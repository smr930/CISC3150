/*
 * [Store Credit Problem]
 * A test case consists of an integer C,
 * which is the store credit you receive, and a sequence of integers, which are prices of
 * the available items. The output for a test case consists of the indices, i and j (i < j),
 * of the two items whose prices add up to the store credit. It is assumed that each test
 * case will have exactly one solution.
*/

#include <iostream>

void storeCredit(int arr[], int size, int credit)
{
	bool found = false;
	for (int i = 0; i < size - 1; i++)
	{
		for (int j = 1; j < size; j++)
		{
			if (arr[i] + arr[j] == credit)
			{
				std::cout << arr[i] << " + " << arr[j] << " = " << credit
					<< ", These values are at index " << i << " and " << j
					<< std::endl;
				found = true;
			}
		}
	}
	if (!found)
	{
		std::cout << "There were no two values that combine to the storecredit"
			<< std::endl;
	}

}

int main()
{
	const int SIZE = 5;
	int credit = 10;
	int values[SIZE] = { 5, 2, 4, 8, 5 };
	storeCredit(values, SIZE, credit);

	std::cin.get();
}
