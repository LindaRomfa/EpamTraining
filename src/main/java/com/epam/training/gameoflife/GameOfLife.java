package com.epam.training.gameoflife;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;

public class GameOfLife extends JFrame implements ActionListener {

    static final int BOARD_DIMENSION_X = 70;
    static final int BOARD_DIMENSION_Y = 70;


    private static final Dimension DEFAULT_WINDOW_SIZE = new Dimension(735, 780);
    private static final Dimension MINIMUM_WINDOW_SIZE = new Dimension(400, 400);
    private static final int BLOCK_SIZE = 10;

    JMenuBar menubar;
    JMenu jMenu;
    JMenuItem miStart;
    JMenuItem miStop;
    JMenuItem miReset;
    GameBoard gameBoard;
    private Thread game;

    public static void main(String[] args) {

        JFrame frame = new GameOfLife();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setTitle("Conway's Game of Life");
        frame.setSize(DEFAULT_WINDOW_SIZE);
        frame.setMinimumSize(MINIMUM_WINDOW_SIZE);
        frame.setLocation((Toolkit.getDefaultToolkit().getScreenSize().width - frame.getWidth()) / 2,
                (Toolkit.getDefaultToolkit().getScreenSize().height - frame.getHeight()) / 2);
        frame.setVisible(true);
    }

    public GameOfLife() {

        gameBoard = new GameBoard();
        add(gameBoard);

        menubar = new JMenuBar();
        jMenu = new JMenu("Menu");

        miStart = new JMenuItem("Start");
        miStop = new JMenuItem("Stop");
        miReset = new JMenuItem("Reset");
        miStart.addActionListener(this);
        miStop.addActionListener(this);
        miReset.addActionListener(this);

        jMenu.add(miStart);
        jMenu.add(miStop);
        jMenu.add(miReset);
        menubar.add(jMenu);
        setJMenuBar(menubar);

    }

    @Override
    public void actionPerformed(ActionEvent e) {

        Object actionEventSource = e.getSource();

        if (actionEventSource.equals(miReset)) {
            gameBoard.init();
        }

        if (actionEventSource.equals(miStart)) {
            startGame();
        }

        if (actionEventSource.equals(miStop)) {
            stopGame();
        }

    }

    private void stopGame() {
        game.interrupt();
    }

    private void startGame() {

        game = new Thread(gameBoard);
        game.start();

    }

    private class GameBoard extends JPanel implements MouseListener, MouseMotionListener, Runnable {

        Board board;

        public GameBoard() {
            addMouseListener(this);
            addMouseMotionListener(this);
            init();
        }

        public void init() {
            board = new DefaultBoard();
            repaint();
        }

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);

            g.setColor(Color.BLACK);
            paintHorizontalLines(g);
            paintVerticalLines(g);

            paintBoard(g);
        }

        private void paintBoard(Graphics g) {
            for (int x = 0; x < BOARD_DIMENSION_X; x++) {
                for (int y = 0; y < BOARD_DIMENSION_Y; y++) {

                    if (board.isAlive(new Coordinate(x,  y))) {
                        g.setColor(Color.blue);
                        g.fillRect(BLOCK_SIZE + (BLOCK_SIZE * x), BLOCK_SIZE + (BLOCK_SIZE * y), BLOCK_SIZE, BLOCK_SIZE);
                    }

                }
            }
        }

        private void paintVerticalLines(Graphics g) {
            for (int y = 0; y <= BOARD_DIMENSION_Y; y++) {
                g.drawLine(BLOCK_SIZE,
                        ((y * BLOCK_SIZE) + BLOCK_SIZE), BLOCK_SIZE * (BOARD_DIMENSION_Y + 1), ((y * BLOCK_SIZE) + BLOCK_SIZE));
            }
        }

        private void paintHorizontalLines(Graphics g) {
            for (int x = 0; x <= BOARD_DIMENSION_X; x++) {
                g.drawLine(((x * BLOCK_SIZE) + BLOCK_SIZE), BLOCK_SIZE,
                        (x * BLOCK_SIZE) + BLOCK_SIZE, BLOCK_SIZE + (BLOCK_SIZE * BOARD_DIMENSION_X));
            }
        }

        private void insertCell(MouseEvent me) {
            int x = me.getPoint().x / BLOCK_SIZE - 1;
            int y = me.getPoint().y / BLOCK_SIZE - 1;
            if ((x >= 0) && (x < BOARD_DIMENSION_X) && (y >= 0) && (y < BOARD_DIMENSION_Y)) {
                board.insertCell(new Coordinate(x, y));
                repaint();
            }
        }

        @Override
        public void mouseClicked(MouseEvent e) {
            // do nothing
        }

        @Override
        public void mousePressed(MouseEvent e) {
            // do nothing

        }

        @Override
        public void mouseReleased(MouseEvent e) {
            insertCell(e);
        }

        @Override
        public void mouseEntered(MouseEvent e) {
            // do nothing
        }

        @Override
        public void mouseExited(MouseEvent e) {
            // do nothing
        }

        @Override
        public void mouseDragged(MouseEvent e) {
            insertCell(e);
        }

        @Override
        public void mouseMoved(MouseEvent e) {
            // do nothing
        }

        @Override
        public void run() {

            board = board.getNextGenerationBoard();
            repaint();

            try {
                Thread.sleep(300);
                run();
            } catch (InterruptedException e) {
            }

        }

    }

}

