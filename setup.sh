#!/bin/bash
# ===========================================
# 🚀 Java Chatbot Setup & Run Script
# ===========================================

# --- 1️⃣ Install Ollama if not installed ---
if ! command -v ollama &> /dev/null
then
    echo "🔧 Installing Ollama..."
    curl -fsSL https://ollama.com/install.sh | sh
else
    echo "✅ Ollama already installed."
fi

# --- 2️⃣ Start Ollama service (in background) ---
echo "🚀 Starting Ollama server..."
ollama serve > /dev/null 2>&1 &

# Give it a few seconds to start
sleep 5

# --- 3️⃣ Download (pull) the Mistral model ---
echo "⬇️ Pulling Mistral model..."
ollama pull gemma4:e4b

# --- 4️⃣ Compile Java chatbot code ---
echo "⚙️ Compiling Java chatbot files..."
cd ~/chatbot || exit

# Optional: download JSON jar if missing
if [ ! -f "json-20210307.jar" ]; then
    echo "📦 Downloading JSON library..."
    wget -q https://repo1.maven.org/maven2/org/json/json/20210307/json-20210307.jar
fi

javac -cp .:json-20210307.jar Chatmatta.java ChatbotGUI.java

if [ $? -ne 0 ]; then
    echo "❌ Compilation failed. Check errors above."
    exit 1
fi

# --- 5️⃣ Run chatbot GUI ---
echo "💬 Launching Java Chatbot..."
java -cp .:json-20210307.jar ChatbotGUI

