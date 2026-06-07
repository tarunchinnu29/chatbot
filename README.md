# 🤖 ChatMatta

ChatMatta is a simple AI chatbot built using Java Swing and Ollama. It provides a desktop chat interface where users can interact with a locally running AI model.

## Features

- Simple and clean Java Swing GUI
- Local AI inference using Ollama
- Fast response generation
- No external API keys required
- Lightweight and easy to run

##  Technologies Used

- Java
- Java Swing
- Ollama
- HTTPURLConnection

## 📂 Project Structure

```text
ChatMatta/
│
├── Chatmatta.java
├── ChatbotGUI.java
├── setup.sh
└── README.md
```

##  Prerequisites

Before running the project, make sure you have:

- Java JDK 8 or higher
- Linux (Ubuntu recommended)
- Ollama installed

## Installation

### Clone the Repository

```bash
git clone https://github.com/tarunchinnu29/ChatMatta.git
cd ChatMatta
```

### Make the Script Executable

```bash
chmod +x setup.sh
```

### Run the Setup Script

```bash
./setup.sh
```

The script will:

- Install Ollama if needed
- Start the Ollama server
- Download the AI model
- Compile Java files
- Launch the chatbot

##  Usage

1. Start the application.
2. Type your message in the input box.
3. Click the **Send** button or press **Enter**.
4. Receive AI-generated responses instantly.

## How It Works

The chatbot sends user prompts to the Ollama API running locally:

```text
http://localhost:11434/api/generate
```

The AI model processes the prompt and returns a response, which is displayed in the GUI.


## 🔮 Future Enhancements

- Dark Mode
- Chat History
- Multiple AI Models
- Better UI Design
- Streaming Responses
- Export Chats

## Author

Tarun 

GitHub: https://github.com/tarunchinnu29

