#include "stdafx.h"

#include<stdio.h>
#include<conio.h>
#include<iostream>
#include<string.h>
#include<stdlib.h>
#include <fstream>
using namespace std;
#define MAX 21
#include "Huffman.h"

int main()
{
    Huffman *huffman=new Huffman();
    huffman->PrintWeight();
}

