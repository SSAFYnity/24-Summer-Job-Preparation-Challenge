#include <string>
#include <vector>
#include <cmath>
using namespace std;

string num="";
bool isPrime(long long n){
    if(n<2) return false;
    for (int i = 2; i <= sqrt(n); ++i) {
        if(n%i==0) return false;
    }
    return true;
}
void converter(int n,int b){
    int r;
    r=n%b;
    n/=b;
    if(n>0) converter(n,b);

    num += to_string(r);
}

int solution(int n, int k) {
    int answer = 0;
    converter(n,k);
    string tmp="";
    for (int i = 0; i < num.size(); ++i) {
        if (num[i] != '0') tmp += num[i];
        else if (num[i] == '0'){
            if(!tmp.empty()){
                if(isPrime(stoll(tmp))) {
                    answer++;
                }
            }
            tmp="";
        }
    }
    if(!tmp.empty()&&isPrime(stoll(tmp))) {
        answer++;
    }
    return answer;
}
