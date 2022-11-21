#include <iostream>
#include <iomanip>
#include <cstdlib>
#include <time.h>

using namespace std;

const int N = 20;

int d[N];

void Sortuj_szybko(int lewy, int prawy)
{
  int i,j,piwot;

  i = (lewy + prawy) / 2;
  piwot = d[i]; d[i] = d[prawy];
  for(j = i = lewy; i < prawy; i++)
  if(d[i] < piwot)
  {
    swap(d[i], d[j]);
    j++;
  }
  d[prawy] = d[j]; d[j] = piwot;
  if(lewy < j - 1)  Sortuj_szybko(lewy, j - 1);
  if(j + 1 < prawy) Sortuj_szybko(j + 1, prawy);
}

int main()
{
  int i;

  srand((unsigned)time(NULL));

  cout << "   Sortowanie szybkie\n"
          "------------------------\n"
          " (C)2005 Jerzy Walaszek \n\n"
          "Przed sortowaniem:\n\n";

  for(i = 0; i < N; i++) d[i] = rand() % 100;
  for(i = 0; i < N; i++) cout << setw(4) << d[i];
  cout << endl;

  Sortuj_szybko(0,N - 1);

  cout << "Po sortowaniu:\n\n";
  for(i = 0; i < N; i++) cout << setw(4) << d[i];
  cout << endl;
  return 0;
}
 