#include <cstdio>
#include <cstring>
#include <iostream>
#include <memory>
#include <sstream>
#include <string>
#include <array>

#include "cpuInfo.h"

using namespace std;

struct cpuStat {
    int user;
    int system;
    int idle;
};

class cpuInfo
{
public:
    void read();
    int getCoresPerSocket() { return _coresPerSocket; }
    int getSocketCount() { return _socketCount; }
    int getl1dCacheSize() { return _l1dCacheSize; }
    int getl1iCacheSize() { return _l1iCacheSize; }
    int getl2CacheSize() { return _l2CacheSize; }
    int getl3CacheSize() { return _l3CacheSize; }
    int getStatUser(int core) { return _currStat[core].user; }
    int getStatSystem (int core) { return _currStat[core].system; }
    int getStatIdle (int core) { return _currStat[core].idle; }
private:
    static const int _maxCores = 64;
    string _architecture;
    string _model;
    int _vaddrbits;
    int _paddrbits;
    bool _littleEndian;
    int _threadsPerCore;
    int _coresPerSocket;
    int _socketCount;
    int _l1dCacheSize;
    int _l1iCacheSize;
    int _l2CacheSize;
    int _l3CacheSize;
    void _parseInfo (string&, string &);
    void _parseStat (char buffer[]);
    void _parseStat (string&);
    cpuStat _prevStat[_maxCores];
    bool _havePrevStat[_maxCores];
    cpuStat _currStat[_maxCores];
};

cpuInfo cpu;

void cpuInfo::_parseInfo (string& key, string &value)
{
    if (key == "Architecture")
        _architecture = value;
    else if (key == "Byte Order")
        _littleEndian == (key == "Little Endian");
    else if (key == "Thread(s) per core")
        _threadsPerCore = stoi (value);
    else if (key == "Core(s) per socket")
        _coresPerSocket = stoi (value);
    else if (key == "Socket(s)")
        _socketCount = stoi (value);
    else if (key == "L1d cache")
        _l1dCacheSize = stoi (value);
    else if (key == "L1i cache")
        _l1iCacheSize = stoi (value);
    else if (key == "L2 cache")
        _l2CacheSize = stoi (value);
    else if (key == "L3 cache")
        _l3CacheSize = stoi (value);
}

void cpuInfo::_parseStat (char buffer[])
{
    if (strncmp (buffer, "cpu", 3))
        return;
    int core=buffer[3]-'0';
    if (core<0||core>=_maxCores)
        return;

    cpuStat stat;
    string line = &buffer[5];
    istringstream stream(line);
    int nice;
    stream >> stat.user >> nice >> stat.system >> stat.idle;
    stat.user += nice;
    if (_havePrevStat[core]) {
        _currStat[core].user = stat.user - _prevStat[core].user;
        _currStat[core].system = stat.system - _prevStat[core].system;
        _currStat[core].idle = stat.idle - _prevStat[core].idle;
    }
    _prevStat[core] = stat;
    _havePrevStat[core] = true;
}

void cpuInfo::read()
{
    std::array<char, 1024> buffer;
    std::unique_ptr<FILE, decltype(&pclose)> pipe(popen("lscpu -B", "r"), pclose);
    if (!pipe) {
        throw std::runtime_error("Failed to execute lscpu!");
    }
    while (fgets(buffer.data(), static_cast<int>(buffer.size()), pipe.get()) != nullptr) {
        string line = buffer.data();
        int delim = line.find(':');
        if (delim == string::npos)
            throw std::runtime_error("Failed to parse " + line);
        string key = line.substr (0, delim);
        delim++;
        while (buffer[delim] == ' ')
            delim++;
        string value = line.substr (delim, string::npos);
        _parseInfo (key, value);
    }
    std::unique_ptr<FILE, decltype(&fclose)> stat(fopen("/proc/stat", "r"), fclose);

    if (!stat) {
        throw std::runtime_error("Failed to open /proc/stat !");
    }

    while (fgets(buffer.data(), static_cast<int>(buffer.size()), stat.get()) != nullptr)
        _parseStat (buffer.data());
}

JNIEXPORT void JNICALL Java_cpuInfo_read (JNIEnv *env, jobject obj) {
    cpu.read();
}

JNIEXPORT jint JNICALL Java_cpuInfo_coresPerSocket (JNIEnv *env, jobject obj) {
   return cpu.getCoresPerSocket();
}

JNIEXPORT jint JNICALL Java_cpuInfo_socketCount (JNIEnv *env, jobject obj) {
   return cpu.getSocketCount();
}

JNIEXPORT jint JNICALL Java_cpuInfo_l1dCacheSize (JNIEnv *env, jobject obj) {
   return cpu.getl1dCacheSize();
}

JNIEXPORT jint JNICALL Java_cpuInfo_l1iCacheSize (JNIEnv *env, jobject obj) {
   return cpu.getl1iCacheSize();
}

JNIEXPORT jint JNICALL Java_cpuInfo_l2CacheSize (JNIEnv *env, jobject obj) {
   return cpu.getl2CacheSize();
}

JNIEXPORT jint JNICALL Java_cpuInfo_l3CacheSize (JNIEnv *env, jobject obj) {
   return cpu.getl3CacheSize();
}

JNIEXPORT jstring JNICALL Java_cpuInfo_getModel (JNIEnv *env, jobject obj)
{
    const char *model = "Intel(R) Core(TM) i5-9400F CPU @ 2.90GHz";

    jstring result = env->NewStringUTF(model);
    return result;
}

JNIEXPORT jint JNICALL Java_cpuInfo_getUserTime (JNIEnv *env, jobject obj, jint core) {
   return cpu.getStatUser (core);
}

JNIEXPORT jint JNICALL Java_cpuInfo_getIdleTime (JNIEnv *env, jobject obj, jint core) {
   return cpu.getStatIdle (core);
}

JNIEXPORT jint JNICALL Java_cpuInfo_getSystemTime (JNIEnv *env, jobject obj, jint core) {
   return cpu.getStatSystem (core);
}

