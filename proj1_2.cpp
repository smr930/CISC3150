/*
* [Store Credit Problem]
* A test case consists of an integer C,
* which is the store credit you receive, and a sequence of integers, which are prices of
* the available items. The output for a test case consists of the indices, i and j (i < j),
* of the two items whose prices add up to the store credit. It is assumed that each test
* case will have exactly one solution.
*/

#include <iostream>
#include <fstream>
#include <cstdlib>
#include <vector>

int *storeCredit(std::vector<int> prices, int items, int credit){
	int  temp[2];
	for (int i = 0; i < items - 1; i++)
	{
		for (int j = i + 1; j < items; j++)
		{
			if (!(prices[i] + prices[j] == credit))
				;
			else {
				temp[0] = i + 1, temp[1] = j + 1;
			}
		}
	}
	return temp;
}

int main() 
{
	std::ifstream infile("input.in");
	if (!infile) 
	{
		std::cout << "could not open input file" << std::endl;
		exit(EXIT_FAILURE);
	}
	std::ofstream outfile("output.out");

	int num_cases, i = 0, items, credit;
	infile >> num_cases;

	for (int i = 0; i < num_cases; i++) 
	{
		infile >> credit;
		infile >> items;

		int price;
		std::vector<int> prices;

		for (int j = 0; j < items; j++) 
		{
			infile >> price;
			prices.push_back(price);
		}

		int *val = storeCredit(prices, items, credit);
		outfile << "Case #" << i + 1 << ": " << val[0] << " " << val[1] << std::endl;
		
	}
	std::cin.get();
}
