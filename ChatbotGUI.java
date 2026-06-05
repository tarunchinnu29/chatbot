import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class ChatbotGUI {
    private JTextArea chatArea;
    private JTextField inputField;
    private JButton sendButton;
    private Chatmatta backend;

    public ChatbotGUI() {
        backend = new Chatmatta("mistral");
        setupUI();
    }

    private void setupUI() {
        JFrame frame = new JFrame("ChatMatta");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(600, 600);
        frame.setLayout(new BorderLayout(10, 10));

        // 🧠 Chat area (larger font)
        chatArea = new JTextArea();
        chatArea.setEditable(false);
        chatArea.setLineWrap(true);
        chatArea.setWrapStyleWord(true);
       chatArea.setFont(new Font("Arial", Font.PLAIN, 18));
chatArea.setFont(new Font("Arial", Font.PLAIN, 18));
 // ⬅️ Increased font size
        chatArea.setMargin(new Insets(10, 10, 10, 10));
        JScrollPane scrollPane = new JScrollPane(chatArea);
        frame.add(scrollPane, BorderLayout.CENTER);

        // 🧠 Input area
        inputField = new JTextField();
        inputField.setFont(new Font("SansSerif", Font.PLAIN, 16)); // ⬅️ Bigger input font
        sendButton = new JButton("Send");
        sendButton.setFont(new Font("SansSerif", Font.BOLD, 16)); // ⬅️ Bigger button text

        JPanel bottom = new JPanel(new BorderLayout(5, 5));
        bottom.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        bottom.add(inputField, BorderLayout.CENTER);
        bottom.add(sendButton, BorderLayout.EAST);
        frame.add(bottom, BorderLayout.SOUTH);

        // 🎯 Action handler
        ActionListener sendAction = e -> {
            String userText = inputField.getText().trim();
            if (!userText.isEmpty()) {
                chatArea.append("🧑 You: " + userText + "\n");
                inputField.setText("");
                new Thread(() -> {
                    chatArea.append("MyBoy: Thinking...\n");
                    String botReply = backend.getResponse(userText);
                    SwingUtilities.invokeLater(() -> {
                        // remove “Thinking…” and replace with real answer
                        int lastLineIndex = chatArea.getText().lastIndexOf("Myboy: Thinking...");
                        if (lastLineIndex >= 0) {
                            chatArea.replaceRange("MyBoy: " + botReply + "\n\n", lastLineIndex, chatArea.getText().length());
                        } else {
                            chatArea.append("MyBoy: " + botReply + "\n\n");
                        }
                    });
                }).start();
            }
        };

        inputField.addActionListener(sendAction);
        sendButton.addActionListener(sendAction);

        frame.setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(ChatbotGUI::new);
    }
}

