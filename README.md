# Problem solved

# This practical will focus on a server application.
In this practical, we will be implementing a protocol for a simple language detection Server.
In an attempt to accommodate the diverse cultures and languages we find at UJ, the university has asked you to develop a simple language detection application. This application will
use a custom protocol, specified below, to ”detect” the language of requests that are sent to it.
In order to test the implemented protocol, we can interact with it using PuTTy and typing requests that facilitate typical usage. The server should run on port 8888 (you may use
any suitable port number for testing purposes) and displays “Ready for incoming connections. . . ” when the server is started.
Upon connection of a client with the server, the server responds with a “HELLO - you
may make 4 requests and I’ll try to detect your language” message. When the client
wants to start a session with the server, it sends a “START” message to the server and it
promptly responds with a “REQUEST or DONE” message - indicating that it is ready to
receive requests. A client may only attempt to query the server 4 times until it displays +
“GOOD BYE - [4] queries answered” message to the client, which is followed by the
connection being terminated.
Once the server is ready, we can start to query it. In order to submit a request, the client sends a message in the following format:
“REQUEST [message]”
Page 1 of 4
Computer Science 2B Practical Assignment 02 2023-08-01
Where the “[message]” part can be any text the client wishes to submit. Once the message is
received by the server, it analyses the message and responds to it in the following way (using
the “0# [Answer]” message format):
 If the message contains the words ”ngiyabonga” or ”mina”, the server responds with I
detect some Zulu here.
 If the message starts with an “Is”, the server randomly responds with either a “Anglais?”,
“English?” or “Maybe Afrikaans?”.
 If the message contains the word “Dumela”, the server should respond with “I greet
you in Sotho!”.
 For anything else, the server should randomly respond with either ”Howzit”, “I’m still
learning” or “No idea”.
However, if the client sends a “DONE” message to the server at any point, it should respond
with a “0# GOOD BYE - [x] queries answered” (where # is the response number and
x is the amount of requests made) and terminates the connection at its end.
