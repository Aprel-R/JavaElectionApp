
import javax.swing.*; // Importing Swing library for GUI components
import java.awt.*; // Importing AWT library for GUI layout and event handling
import java.awt.event.ActionEvent; // Importing ActionEvent class for action events
import java.awt.event.ActionListener; // Importing ActionListener interface for action event handling

public class App extends JFrame { // Define a class named "App" which extends JFrame for GUI window
    private final char[] votes = new char[5]; // Array to store votes
    private JTextArea voteArea; // TextArea to display votes
    private int votesReceived = 0; // Counter to keep track of votes received

    public App() { // Constructor for App class
        setTitle("Election Vote Casting Application"); // Set the title of the JFrame
        setSize(400, 300); // Set the size of the JFrame
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Set default close operation
        setLayout(new BorderLayout()); // Set layout manager for the JFrame

        JPanel votePanel = new JPanel(); // Create a panel for vote buttons
        votePanel.setLayout(new GridLayout(3, 1)); // Set layout for votePanel

        JButton voteButtonA = new JButton("Vote for A"); // Create a button for voting for candidate A
        JButton voteButtonB = new JButton("Vote for B"); // Create a button for voting for candidate B
        voteArea = new JTextArea(); // Create a text area to display votes
        voteArea.setEditable(false); // Make the text area uneditable

        votePanel.add(voteButtonA); // Add voteButtonA to votePanel
        votePanel.add(voteButtonB); // Add voteButtonB to votePanel
        add(votePanel, BorderLayout.NORTH); // Add votePanel to the JFrame's north region
        add(new JScrollPane(voteArea), BorderLayout.CENTER); // Add voteArea (with JScrollPane) to the JFrame's center region

        voteButtonA.addActionListener(new ActionListener() { // Add action listener to voteButtonA
            @Override
            public void actionPerformed(ActionEvent e) { // Define action when voteButtonA is clicked
                castVote('A'); // Cast a vote for candidate A
            }
        });

        voteButtonB.addActionListener(new ActionListener() { // Add action listener to voteButtonB
            @Override
            public void actionPerformed(ActionEvent e) { // Define action when voteButtonB is clicked
                castVote('B'); // Cast a vote for candidate B
            }
        });

        setVisible(true); // Make the JFrame visible
    }

    
    private void castVote(char candidate) { // Method to cast a vote for a candidate
       if (votesReceived < votes.length) { // Check if there are available slots for votes
        votes[votesReceived] = candidate; // Store the vote for the candidate
        votesReceived++; // Increment the vote counter
        updateUI(); // Update the UI to reflect the new vote
        if (votesReceived == votes.length) { // Check if maximum number of votes has been reached
            determineWinner(); // Determine the winner based on current votes
        }
     }
    }


    private void updateUI() { // Method to update the UI with current votes
        StringBuilder voteSummary = new StringBuilder(); // Create a StringBuilder to build the vote summary
        voteSummary.append("Votes Cast:\n"); // Add header for vote summary
        for (int i = 0; i < votesReceived; i++) { // Iterate through all received votes
            voteSummary.append("Electorate ").append(i + 1).append(": ").append(votes[i]).append("\n"); // Append vote details to summary
        }
        voteArea.setText(voteSummary.toString()); // Set the text of the vote area to the built summary
    }

    private void determineWinner() { // Method to determine the winner based on current votes
        int votesForA = 0; // Counter for votes for candidate A
        int votesForB = 0; // Counter for votes for candidate B

        for (char vote : votes) { // Iterate through all received votes
            if (vote == 'A') { // If the vote is for candidate A
                votesForA++; // Increment votes for A
            } else if (vote == 'B') { // If the vote is for candidate B
                votesForB++; // Increment votes for B
            }
        }

        char winner = (votesForA > votesForB) ? 'A' : 'B'; // Determine the winner based on votes
        JOptionPane.showMessageDialog(this, "Winner is Candidate " + winner); // Show a message dialog with the winner
    }

    public static void main(String[] args) { // Main method to start the application
        SwingUtilities.invokeLater(new Runnable() { // Ensure GUI operations are performed on the Event Dispatch Thread
            @Override
            public void run() { // Run method for the Runnable
                new App(); // Create an instance of the App class (starts the application)
            }
        });
    }
}
