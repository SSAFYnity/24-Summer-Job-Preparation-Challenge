#include <string>
#include <iostream>
#include <algorithm>
#include <unordered_map>
#include <unordered_set>
using namespace std;
unordered_map<string, int> m1, m2;
unordered_set<string> ss;
int len1, len2, cc, unc;

int make_map(unordered_map<string, int> &m, string &s) {
    transform(s.begin(), s.end(), s.begin(), ::tolower);
    string temp;
    int cnt = 0;
    for(int i=0; i<s.size()-1; i++) {
        temp = "";
        if(s[i]>='a' && s[i]<='z' && s[i+1] >= 'a' && s[i]<='z') {
            temp+=s[i];
            temp+=s[i+1];
            m[temp]++;
            ss.insert(temp);
            cnt++;
        }
    }
    return cnt;
}

void crossCnt()  {
    for(auto str : ss) {
        cc += min(m1[str], m2[str]);
    }
}

int solution(string str1, string str2) {
    int answer = 0;
    
    len1 = make_map(m1, str1);
    len2 = make_map(m2, str2);
    int total = len1+len2;

    crossCnt();

    unc = total - cc;
    if(!cc && !unc) return 65536;
    return (double) cc/ (double) unc * 65536;
}