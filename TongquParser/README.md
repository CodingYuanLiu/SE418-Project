# Parser
Parse the result of Tongqu crawler and find out which activities contain "素拓" .
## Log
Basic function compeleted (it's very easy). Simply use Jieba tool to cut the chinese sentences into words and check the key words in the word list.
The additional dictionary of Jieba is stored in /src/main/resources/jieba.dict, and the JSON file "test,json" is used alternatively,