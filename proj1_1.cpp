/*
 *Reverse Words is a string. Given a list of space separated words,
 *reverse the order of the words.
 */

#include <iostream>
#include <algorithm>
#include <vector>
#include <string>

void reverseWords(std::string str)
{
	std::vector<std::string> words;
	std::string::size_type pos = 1;

	while (pos != std::string::npos)
	{
		pos = str.find(" ");
		if (pos != std::string::npos)
		{
			words.push_back(std::string(str.begin(), str.begin() + pos));
			str.erase(str.begin(), str.begin() + pos + 1);
		}
		else
		{
			words.push_back(std::string(str.begin(), str.end()));
			str.erase(str.begin(), str.end());
		}
	}
	reverse(words.begin(), words.end());

	for (int i = 0; i < words.size(); i++)
	{
		str.append(words[i].append(" "));
	}
	std::cout << str << std::endl;
}

int main()
{
	std::string str1 = "this is a test";
	std::string str2 = "foobar";
	std::string str3 = "all your base";

	reverseWords(str1);
	reverseWords(str2);
	reverseWords(str3);

	std::cin.get();
}
