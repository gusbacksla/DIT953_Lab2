import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;

public class GUI extends JFrame implements UI {
    private ArrayList<IVehicle> vehicles;

    private ArrayList<IUIListener> listeners = new ArrayList<>();

    private static final int X = 900;
    private static final int Y = 840;

    private final DrawPanel drawPanel = new DrawPanel(X, Y - 240);

    private final JPanel controlPanel = new JPanel();

    private final JPanel gasPanel = new JPanel();

    private final JSpinner gasSpinner = new JSpinner();
    int gasAmount = 0;
    private final JLabel gasLabel = new JLabel("Amount of gas");

    private final JButton gasButton = new JButton("Gas");
    private final JButton brakeButton = new JButton("Brake");
    private final JButton turboOnButton = new JButton("Turbo on");
    private final JButton turboOffButton = new JButton("Turbo off");
    private final JButton lowerBedButton = new JButton("Lift Up");
    private final JButton liftBedButton = new JButton("Lift Down");
    private final JButton addCarButton = new JButton("Add Car");
    private final JButton removeCarButton = new JButton("Remove Car");

    private final JButton startButton = new JButton("Start All");
    private final JButton stopButton = new JButton("Stop All");

    private final JButton newUIButton = new JButton("Reset UI");


    public GUI(ArrayList<IVehicle> vehicles, String title) throws IOException {
        this.vehicles = vehicles;
        initComponents(title);
        addCarsToPanel();
    }

    public int getXMax(){
        return drawPanel.getXMax();
    }
    public int getYMax(){
        return drawPanel.getYMax();
    }

    @Override
    public void rebuildVehicleList(ArrayList<IVehicle> vehicles) {
        this.vehicles = vehicles;
        drawPanel.reset();
        addCarsToPanel();
    }

    private void addCarsToPanel(){
        for(IVehicle v : vehicles){
            drawPanel.addCar(v.getModelName(), v.getPosition());
        }
        drawPanel.repaint();
    }

    @Override
    public void update() {
        drawPanel.repaint();
    }

    @Override
    public void addUIListener(IUIListener listener) {
        listeners.add(listener);
    }

    private void initComponents(String title) {

        this.setTitle(title);
        this.setPreferredSize(new Dimension(X, Y));
        this.setLayout(new FlowLayout(FlowLayout.LEFT, 0, 0));

        this.add(drawPanel);



        gasSpinner.setModel(new SpinnerNumberModel(0,  0,  100,  1));
        gasSpinner.addChangeListener(e -> gasAmount = (int) ((JSpinner) e.getSource()).getValue());

        gasPanel.setLayout(new BorderLayout());
        gasPanel.add(gasLabel, BorderLayout.PAGE_START);
        gasPanel.add(gasSpinner, BorderLayout.PAGE_END);

        this.add(gasPanel);

        controlPanel.setLayout(new GridLayout(2, 5));

        controlPanel.add(gasButton, 0);
        controlPanel.add(turboOnButton, 1);
        controlPanel.add(lowerBedButton, 2);
        controlPanel.add(addCarButton, 3);
        controlPanel.add(brakeButton, 4);
        controlPanel.add(turboOffButton, 5);
        controlPanel.add(liftBedButton, 6);
        controlPanel.add(removeCarButton, 7);
        controlPanel.setPreferredSize(new Dimension((X / 2) + 4, 200));
        this.add(controlPanel);
        controlPanel.setBackground(Color.CYAN);


        startButton.setBackground(Color.blue);
        startButton.setForeground(Color.green);
        startButton.setPreferredSize(new Dimension(X / 5 - 15, 200));
        this.add(startButton);


        stopButton.setBackground(Color.red);
        stopButton.setForeground(Color.black);
        stopButton.setPreferredSize(new Dimension(X / 5 - 15, 200));
        this.add(stopButton);

        this.add(newUIButton);


        gasButton.addActionListener(e -> {
            try {
                sendEvent("gas", gasAmount);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        });

        brakeButton.addActionListener(e -> {
            try {
                sendEvent("brake", gasAmount);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        });
        /////////////////////////
        turboOnButton.addActionListener(e -> {
            try {
                sendEvent("turboOn", 0);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        });

        turboOffButton.addActionListener(e -> {
            try {
                sendEvent("turboOff", 0);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        });

        liftBedButton.addActionListener(e -> {
            try {
                sendEvent("liftBed", 0);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        });

        lowerBedButton.addActionListener(e -> {
            try {
                sendEvent("lowerBed", 0);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        });

        startButton.addActionListener(e -> {
            try {
                sendEvent("startCars", 0);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        });

        stopButton.addActionListener(e -> {
            try {
                sendEvent("stopCars", 0);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        });

        addCarButton.addActionListener(e -> {
            try {
                sendEvent("addCar", 0);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        });

        removeCarButton.addActionListener(e -> {
            try {
                sendEvent("deleteCar", 0);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        });

        newUIButton.addActionListener(e -> {
            try {
                sendEvent("newUI", 0);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        });

        // Make the frame pack all it's components by respecting the sizes if possible.
        this.pack();

        // Get the computer screen resolution
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        // Center the frame
        this.setLocation(dim.width / 2 - this.getSize().width / 2, dim.height / 2 - this.getSize().height / 2);
        // Make the frame visible
        this.setVisible(true);
        // Make sure the frame exits when "x" is pressed
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    private void sendEvent(String event, double value) throws Exception {
        for(IUIListener l : listeners){ l.event(new UIEvent(event, value)); }
    }


    private static class DrawPanel extends JPanel{

        ArrayList<VehicleImage> images = new ArrayList<>();

        private final BufferedImage volvoImage = ImageIO.read(DrawPanel.class.getResourceAsStream("pics/Volvo240.jpg"));
        private final BufferedImage saabImage = ImageIO.read(DrawPanel.class.getResourceAsStream("pics/Saab95.jpg"));
        private final BufferedImage scaniaImage = ImageIO.read(DrawPanel.class.getResourceAsStream("pics/Scania.jpg"));

        private final int x;
        private final int y;

        // Initializes the panel and reads the images
        public DrawPanel(int x, int y) throws IOException {
            this.x = x;
            this.y = y;
            this.setDoubleBuffered(true);
            this.setPreferredSize(new Dimension(x, y));
            this.setBackground(Color.green);
        }

        private void addCar(String carType, DPoint position){
            switch (carType){
                case ("Saab95") : {
                    images.add(new VehicleImage(saabImage, position));
                    break;
                }
                case ("Volvo240") : {
                    images.add(new VehicleImage(volvoImage, position));
                    break;
                }
                case ("Scania") : {
                    images.add(new VehicleImage(scaniaImage, position));
                    break;
                }
            }
        }

        public void reset(){
            images = new ArrayList<>();
        }

        public int getXMax(){ return x; }
        public int getYMax(){ return y; }

        // This method is called each time the panel updates/refreshes/repaints itself
        // TODO: Change to suit your needs.
        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            for (VehicleImage i : images){
                g.drawImage(i.getImage(), (int) i.getX(), (int) i.getY(), null); // see javadoc for more info on the parameters
            }
        }

        private class VehicleImage {
            private BufferedImage image;
            private DPoint position;
            public VehicleImage(BufferedImage image, DPoint position){
                this.image = image;
                this.position = position;
            }

            public BufferedImage getImage(){ return image;}

            public double getX(){ return position.getX(); }

            public double getY(){ return position.getY(); }
        }
    }
}
