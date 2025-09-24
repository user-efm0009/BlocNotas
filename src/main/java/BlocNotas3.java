import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.Objects;

public class BlocNotas3 extends JFrame {

    public BlocNotas3() {
        setTitle("Bloc de Notas - Versión 3");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(550, 500);
        setLocationRelativeTo(null);

        ImageIcon fondoIcon = new ImageIcon(Objects.requireNonNull(getClass().getResource("/fondodeimagen2.jpg")));
        Image fondo = fondoIcon.getImage();

        ImageIcon originalIcon = new ImageIcon(Objects.requireNonNull(getClass().getResource("/logo.png")));
        Image imagenEscalada = originalIcon.getImage().getScaledInstance(24, 24, Image.SCALE_SMOOTH);
        ImageIcon iconoEmoji = new ImageIcon(imagenEscalada);

        JTabbedPane pestañas = new JTabbedPane();

        // Panel Login
        BackgroundPanel loginPanel = new BackgroundPanel(fondo);
        loginPanel.add(createFormularioPanel(iconoEmoji, "INICIO DE SESIÓN"));

        // Panel Registro
        BackgroundPanel registroPanel = new BackgroundPanel(fondo);
        registroPanel.add(createFormularioPanel(iconoEmoji, "REGISTRO DE NUEVO USUARIO"));

        pestañas.addTab("🔐 Inicio de sesión", loginPanel);
        pestañas.addTab("📝 Registro", registroPanel);

        add(pestañas);
        setVisible(true);
    }

    private JPanel createFormularioPanel(ImageIcon iconoEmoji, String titulo) {
        JPanel panel = new JPanel();
        panel.setOpaque(false);
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

        JLabel logoLabel = new JLabel(titulo, iconoEmoji, JLabel.LEFT);
        logoLabel.setFont(new Font("Sanserif", Font.BOLD, 24));
        logoLabel.setForeground(Color.BLACK);
        logoLabel.setToolTipText("Bienvenido al Bloc de Notas");

        JPanel logoPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        logoPanel.setOpaque(false);
        logoPanel.add(logoLabel);
        panel.add(logoPanel);
        panel.add(Box.createRigidArea(new Dimension(0, 20)));

        // Usuario y contraseña
        JTextField usuarioField = new JTextField();
        usuarioField.setPreferredSize(new Dimension(200, usuarioField.getPreferredSize().height));
        usuarioField.setToolTipText("Introduce tu usuario");

        JPasswordField contraseñaField = new JPasswordField();
        contraseñaField.setPreferredSize(new Dimension(200, contraseñaField.getPreferredSize().height));
        contraseñaField.setToolTipText("Introduce tu contraseña");

        JPanel camposPanel = new JPanel(new GridBagLayout());
        camposPanel.setOpaque(false);
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        gbc.gridx = 0;
        gbc.gridy = 0;
        camposPanel.add(new JLabel("👤 Usuario:"), gbc);
        gbc.gridx = 1;
        camposPanel.add(usuarioField, gbc);
        gbc.gridx = 0;
        gbc.gridy = 1;
        camposPanel.add(new JLabel("🔒 Contraseña:"), gbc);
        gbc.gridx = 1;
        camposPanel.add(contraseñaField, gbc);

        panel.add(camposPanel);
        panel.add(Box.createRigidArea(new Dimension(0, 10)));

        // Edad
        JLabel edadLabel = new JLabel("Edad:");
        edadLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        edadLabel.setForeground(Color.BLACK);
        panel.add(edadLabel);

        JSpinner edadSpinner = new JSpinner(new SpinnerNumberModel(18, 0, 100, 1));
        edadSpinner.setMaximumSize(new Dimension(100, 25));
        edadSpinner.setAlignmentX(Component.CENTER_ALIGNMENT);
        edadSpinner.setToolTipText("Selecciona tu edad");
        panel.add(edadSpinner);
        panel.add(Box.createRigidArea(new Dimension(0, 10)));

        // Barra de carga
        JProgressBar barraCarga = new JProgressBar(0, 100);
        barraCarga.setValue(0);
        barraCarga.setStringPainted(true);
        barraCarga.setToolTipText("Progreso de carga ficticio");
        panel.add(barraCarga);
        panel.add(Box.createRigidArea(new Dimension(0, 10)));

        // Slider de seguridad
        JSlider seguridadSlider = new JSlider(0, 10, 5);
        seguridadSlider.setMajorTickSpacing(2);
        seguridadSlider.setPaintTicks(true);
        seguridadSlider.setPaintLabels(true);
        seguridadSlider.setToolTipText("Nivel de seguridad");
        JPanel seguridadLabelPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        seguridadLabelPanel.setOpaque(false);
        JLabel seguridadLabel = new JLabel("Nivel de seguridad:");
        seguridadLabel.setForeground(Color.BLACK);
        seguridadLabelPanel.add(seguridadLabel);
        panel.add(seguridadLabelPanel);
        panel.add(seguridadSlider);
        panel.add(Box.createRigidArea(new Dimension(0, 10)));

        // Checkbox
        JCheckBox terminosCheck = new JCheckBox("Aceptar términos y condiciones");
        terminosCheck.setAlignmentX(Component.CENTER_ALIGNMENT);
        terminosCheck.setOpaque(false);
        terminosCheck.setToolTipText("Debes aceptar los términos");
        panel.add(terminosCheck);
        panel.add(Box.createRigidArea(new Dimension(0, 10)));

        // Botones
        JButton aceptarButton = new JButton("Aceptar");
        JButton limpiarButton = new JButton("Eliminar");

        aceptarButton.setBackground(new Color(0, 153, 0));
        aceptarButton.setForeground(Color.WHITE);
        aceptarButton.setToolTipText("Confirmar acción");

        limpiarButton.setBackground(new Color(204, 0, 0));
        limpiarButton.setForeground(Color.WHITE);
        limpiarButton.setToolTipText("Borrar campos");

        limpiarButton.addActionListener((ActionEvent e) -> {
            usuarioField.setText("");
            contraseñaField.setText("");
            terminosCheck.setSelected(false);
            seguridadSlider.setValue(5);
            barraCarga.setValue(0);
            edadSpinner.setValue(18);
        });

        JPanel botonesPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 0));
        botonesPanel.setOpaque(false);
        botonesPanel.add(aceptarButton);
        botonesPanel.add(limpiarButton);
        panel.add(botonesPanel);

        return panel;
    }

    static class BackgroundPanel extends JPanel {
        private final Image fondo;

        public BackgroundPanel(Image fondo) {
            this.fondo = fondo;
            setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
            setBorder(BorderFactory.createEmptyBorder(20, 40, 20, 40));
        }

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            g.drawImage(fondo, 0, 0, getWidth(), getHeight(), this);
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(BlocNotas3::new);
    }
}
