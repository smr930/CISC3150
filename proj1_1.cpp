/*
 *Reverse Words is a string. Given a list of space separated words,
 *reverse the order of the words.
 */

#include <iostream>
#include <sstream>
#include <vector>
#include <string>
#include <fstream>

std::string reverseWords(const std::string& line)
{
	std::istringstream buffer(line);
	std::string word, revStr;
	std::vector<std::string> hold;
	int wordCount = 0;

	while (buffer >> word)
	{
		hold.push_back(word);
		wordCount++;
	}
	for (int i = wordCount - 1; i >= 0; i--)
	{
		revStr += hold[i];
		revStr += " ";
	}
	return revStr;
}

int main()
{
	std::ifstream infile("input.in");
	std::ofstream oufile("output.out");
	if (!infile)
	{
		std::cout << "Could not open input file" << std::endl;
	}
	if (!oufile)
	{
		std::cout << "Could ont open output file" << std::endl;
	}
	std::string line;
	int test_cases;
	infile >> test_cases;    
	getline(infile, line); 

	for (int x = 1; std::getline(infile, line); x++)
	{
		std::string all = reverseWords(line);
		oufile << "Case #" << x << ": " << all << std::endl;
	}
	infile.close();
	oufile.close();
	std::cin.get();
}
