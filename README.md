# Java Multithreaded Chat Server

A multi-threaded Java TCP chat application that allows multiple clients to connect to a server, exchange messages in real time and receive chat history upon joining.

## Project Overview

This project demonstrates the implementation of a multi-threaded client-server architecture using Java TCP sockets.

Each connected client is handled in a separate thread, allowing multiple users to communicate simultaneously. Messages are broadcast to all connected clients, while newly connected users automatically receive the previous chat history.

## Features

- Multi-threaded TCP server
- Multiple client support
- Real-time message broadcasting
- Chat history synchronization
- Username-based messaging
- Automatic client connection handling

## Technologies

- Java
- TCP Sockets
- Multithreading
- Object-Oriented Programming (OOP)
- IntelliJ IDEA

## Skills Demonstrated

- Network Programming
- Concurrent Programming
- Java Threads
- TCP Client-Server Architecture
- Socket Communication
- Object-Oriented Design

## How it Works

```text
Client 1
      │
Client 2
      │
Client 3
      │
      ▼
 TCP Socket
      │
      ▼
Server
      │
      ▼
ClientHandler (Thread)
      │
      ▼
Broadcast Message
```

## Installation

Clone the repository:

```bash
git clone https://github.com/petzmitev/java-multithreaded-chat-server.git
```

Open the project in IntelliJ IDEA.

Compile and run:

- `Server/Main.java`
- `Client/ClientMain.java`

## Demo

Screenshots demonstrating the application will be added here.

## Future Improvements

- Private messaging
- User authentication
- Graphical User Interface (GUI)
- Message persistence using a database
- End-to-end encryption

## Author

Peter Mitev