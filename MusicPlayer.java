//IMPORTING STATEMENTS.
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

//MAIN CLASS that IMPLEMENTS THE ACTION LISTENER function
public class MusicPlayer implements ActionListener {

    // Main Frame and Main Label of the Application.
    public static JFrame aFrame;
    public static JLabel appProject;

    // Sidebar Panel 1 of the Application
    public static JLabel album;
    public static JPanel firstP;
    public static JPanel firstSP;
    public static JPanel secondSP;

    // Sidebar Panel 2 of the Application
    public static JLabel nPlaylist;
    public static JLabel albumCoverLabel;
    private static JPanel thirdSP;
    public static JList playlist;
    public static JPanel fourthSP;

    // Main Background and name of the Application
    public static JLabel ApplicationName;
    public static JLabel CoverLabel1;

    // Displaying the name of the song
    public static JLabel songLabel;

    // Horizontal bars...
    private static JPanel thirdP1;
    private static JPanel thirdP2;

    // Buttons of the Application
    private static JButton playButton;
    private static JButton previousButton;
    private static JButton nextButton;

    //CRUCIAL for playing music
    private static Clip clip;
    static AudioInputStream audioInputStream;
    static File[] musicFiles;
    static int currentSongIndex = 0;

    //THE MAIN METHOD
    public static void main(String[] args) {

        //Declaration of the Main JFrame.
        aFrame = new JFrame("CASE STUDY!");
        aFrame.setBounds(0, 0, 1400, 700);
        aFrame.setLayout(null);
        aFrame.setResizable(false);
        aFrame.setVisible(true);
        aFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //Declaration of the Main Label
        appProject = new JLabel(" Java Music Player");
        appProject.setHorizontalTextPosition(JLabel.CENTER);
        appProject.setVerticalAlignment(JLabel.TOP);
        appProject.setForeground(Color.WHITE);
        appProject.setBackground(new Color(14, 188, 14));
        appProject.setOpaque(true);
        appProject.setFont(new Font("Segoe UI", Font.PLAIN, 15));
        appProject.setBounds(0, 0, 1400, 22);
        aFrame.add(appProject);

        // Literally the "ALBUM" Label
        album = new JLabel();
        album.setText("Album");
        album.setVerticalAlignment(JLabel.TOP);
        album.setHorizontalAlignment(JLabel.CENTER);
        album.setFont(new Font("Segoe UI", Font.BOLD, 40));
        album.setForeground(Color.WHITE);
        album.setLayout(null);

        //First Main Panel
        firstP = new JPanel();
        firstP.setBackground(new Color(17, 17, 17));
        firstP.setOpaque(true);
        firstP.setBounds(0, 22, 400, 70);
        firstP.add(album);
        aFrame.add(firstP);

        //First SubPanel
        firstSP = new JPanel();
        firstSP.setBackground(new Color(17, 17, 17));
        firstSP.setOpaque(true);
        firstSP.setBounds(0, 90, 40, 260);
        aFrame.add(firstSP);

        //Second SubPanel
        secondSP = new JPanel();
        secondSP.setBackground(new Color(17, 17, 17));
        secondSP.setOpaque(true);
        secondSP.setBounds(360, 90, 40, 260);
        aFrame.add(secondSP);

        // AlbumCover (Image)
        ImageIcon cover = new ImageIcon("Media\\NewWave2.jfif");
        albumCoverLabel = new JLabel(cover);

        // Name of the Album.
        nPlaylist = new JLabel();
        nPlaylist.setText("Spontaneous Music");
        nPlaylist.setVerticalTextPosition(JLabel.TOP);
        nPlaylist.setHorizontalTextPosition(JLabel.LEFT);
        nPlaylist.setFont(new Font("Segoe UI", Font.PLAIN, 30));
        nPlaylist.setForeground(Color.WHITE);
        nPlaylist.setBounds(40, 90, 360, 250);
        albumCoverLabel.add(nPlaylist);
        albumCoverLabel.setBounds(40, 90, 360, 250);
        aFrame.add(albumCoverLabel);

        //Third SubPanel
        thirdSP = new JPanel();
        thirdSP.setBackground(new Color(17, 17, 17));
        thirdSP.setOpaque(true);
        thirdSP.setBounds(0, 280, 400, 90);
        aFrame.add(thirdSP);

        //Fourth SubPanel
        fourthSP = new JPanel();
        fourthSP.setBackground(new Color(17, 17, 17));
        fourthSP.setOpaque(true);
        fourthSP.setBounds(0, 370, 400, 700);
        aFrame.add(fourthSP);

        //The folder or files seeker.
        File folder = new File("Insert your File Path here.");
        File[] files = folder.listFiles();
        DefaultListModel<String> listModel = new DefaultListModel<>();
        assert files != null;
        for (File file : files) {
            if (file.isFile() && file.getName().endsWith(".wav")) {
                listModel.addElement(file.getName());
            }
        }

        // The JList of .wav files.
        playlist = new JList<>(listModel);
        playlist.setBackground(new Color(17, 17, 17));
        playlist.setOpaque(true);
        playlist.setFont(new Font("Segoe UI", Font.BOLD, 14));
        playlist.setForeground(Color.WHITE);
        playlist.setBounds(0, 370, 400, 700);
        JScrollPane scrollPane = new JScrollPane(playlist);
        fourthSP.add(scrollPane, BorderLayout.CENTER);
        fourthSP.add(playlist);

        //Main Background Wallpaper and name of the Application. (Formerly known as the Second Panel.)
        ImageIcon cover1 = new ImageIcon("Media\\NewWallpaper.png");
        CoverLabel1 = new JLabel(cover1);
        ApplicationName = new JLabel();
        ApplicationName.setText("I LoveMusic 1.0");
        ApplicationName.setVerticalTextPosition(JLabel.TOP);
        ApplicationName.setHorizontalTextPosition(JLabel.CENTER);
        ApplicationName.setFont(new Font("Segoe UI", Font.BOLD, 65));
        ApplicationName.setForeground(Color.WHITE);
        ApplicationName.setBounds(400, 22, 998, 478);
        CoverLabel1.add(ApplicationName);
        CoverLabel1.setBounds(400, 22, 998, 478);
        aFrame.add(CoverLabel1);

        //Declaration of the Method MusicPlayer();
        new MusicPlayer();

    }
        //Method
        public MusicPlayer() {

            //Label of the Song.
            songLabel = new JLabel();
            songLabel.setText(" Playing: ");
            songLabel.setVerticalAlignment(JLabel.TOP);
            songLabel.setHorizontalAlignment(JLabel.LEFT);
            songLabel.setFont(new Font("Segoe UI", Font.BOLD, 30));
            songLabel.setForeground(new Color(14,188,14));
            songLabel.setLayout(null);
            songLabel.setBounds(400, 500, 1098, 85);

            // Third Panel 1
            thirdP1 = new JPanel();
            thirdP1.setBackground(new Color(23, 23, 23));
            thirdP1.setOpaque(true);
            thirdP1.setBounds(400, 500, 1098, 83);
            thirdP1.setLayout(new BoxLayout(thirdP1, BoxLayout.X_AXIS));
            thirdP1.setVisible(true);

            //Third Panel 2
            thirdP2 = new JPanel();
            thirdP2.setBackground(new Color(31, 31, 31));
            thirdP2.setOpaque(true);
            thirdP2.setBounds(400, 509, 1098, 189);
            thirdP2.setLayout(new BoxLayout(thirdP2, BoxLayout.X_AXIS));
            thirdP2.setVisible(true);

            // The Music Files' pathname
            musicFiles = new File
                    ("Insert your File Path here.").listFiles();
                     playMusic(musicFiles[currentSongIndex]);

                     //The PLAY or PAUSE Button
                     playButton = new JButton();
                     playButton.setText("PLAY/PAUSE");
                     playButton.setFont(new Font("Segoe UI", Font.BOLD, 18));
                     playButton.setForeground(Color.WHITE);
                     playButton.setBackground(new Color(27, 27, 27));
                     playButton.setOpaque(true);
                     playButton.addActionListener(this);

                     //The PREVIOUS Button
                     previousButton = new JButton();
                     previousButton.setText("PREVIOUS");
                     previousButton.setFont(new Font("Segoe UI", Font.BOLD, 18));
                     previousButton.setForeground(Color.WHITE);
                     previousButton.setBackground(new Color(27, 27, 27));
                     previousButton.setOpaque(true);
                     previousButton.addActionListener(this);

                     //The NEXT Button
                     nextButton = new JButton();
                     nextButton.setText("NEXT");
                     nextButton.setFont(new Font("Segoe UI", Font.BOLD, 18));
                     nextButton.setForeground(Color.WHITE);
                     nextButton.setBackground(new Color(27, 27, 27));
                     nextButton.setOpaque(true);
                     nextButton.setVisible(true);
                     nextButton.addActionListener(this);

            //Adding the songLabel and three Buttons to Third Panel 1 and 2
            thirdP1.add(songLabel);
            thirdP2.add(playButton);
            thirdP2.add(previousButton);
            thirdP2.add(nextButton);
            // Adding to the Main Frame of the Application.
            aFrame.add(thirdP1);
            aFrame.add(thirdP2);
    }

    //METHOD for the Playing the WAV music file.
    private static void playMusic(File musicFile) {
        try {
            if (clip != null) {
                clip.stop();
                clip.close();
            }
            audioInputStream = AudioSystem.getAudioInputStream(musicFile);
            clip = AudioSystem.getClip();
            clip.open(audioInputStream);
            clip.start();
            songLabel.setText("Playing: " + musicFile.getName());
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    //FUNCTION of all BUTTONS(PLAY/PAUSE, PREVIOUS, and NEXT)
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == previousButton) {
            currentSongIndex = (currentSongIndex - 1 + musicFiles.length) % musicFiles.length;
            playMusic(musicFiles[currentSongIndex]);
        } else if (e.getSource() == playButton) {
            if (clip != null) {
                if (clip.isRunning()) {
                    clip.stop();
                } else {
                    clip.start();
                }
            }
        } else if (e.getSource() == nextButton) {
            currentSongIndex = (currentSongIndex + 1) % musicFiles.length;
            playMusic(musicFiles[currentSongIndex]);
        }
    }
}
