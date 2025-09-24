import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Objects;

public class BlocNotas3 extends JFrame {

    public JPanel panel1;

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

        BackgroundPanel loginPanel = new BackgroundPanel(fondo);
        loginPanel.add(crearFormulario(iconoEmoji, "INICIO DE SESIÓN"));

        BackgroundPanel registroPanel = new BackgroundPanel(fondo);
        registroPanel.add(crearFormulario(iconoEmoji, "REGISTRO DE NUEVO USUARIO"));

        pestañas.addTab("🔐 Inicio de sesión", loginPanel);
        pestañas.addTab("📝 Registro", registroPanel);

        add(pestañas);
        setVisible(true);
    }

    private JPanel crearFormulario(ImageIcon iconoEmoji, String titulo) {
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

        JPanel edadPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        edadPanel.setOpaque(false);
        JLabel edadLabel = new JLabel("🎂 Edad:");
        edadLabel.setForeground(Color.BLACK);
        edadPanel.add(edadLabel);
        panel.add(edadPanel);

        JSpinner edadSpinner = new JSpinner(new SpinnerNumberModel(18, 0, 100, 1));
        edadSpinner.setMaximumSize(new Dimension(100, 25));
        edadSpinner.setToolTipText("Selecciona tu edad");
        panel.add(edadSpinner);
        panel.add(Box.createRigidArea(new Dimension(0, 10)));

        JProgressBar barraCarga = new JProgressBar(0, 100);
        barraCarga.setValue(0);
        barraCarga.setStringPainted(true);
        barraCarga.setToolTipText("Progreso de carga ficticio");
        panel.add(barraCarga);
        panel.add(Box.createRigidArea(new Dimension(0, 10)));

        JPanel seguridadPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        seguridadPanel.setOpaque(false);
        JLabel seguridadLabel = new JLabel("🛡️ Nivel de seguridad:");
        seguridadLabel.setForeground(Color.BLACK);
        seguridadPanel.add(seguridadLabel);
        panel.add(seguridadPanel);

        JSlider seguridadSlider = new JSlider(0, 10, 5);
        seguridadSlider.setMajorTickSpacing(2);
        seguridadSlider.setPaintTicks(true);
        seguridadSlider.setPaintLabels(true);
        seguridadSlider.setToolTipText("Nivel de seguridad");
        panel.add(seguridadSlider);
        panel.add(Box.createRigidArea(new Dimension(0, 10)));

        JCheckBox terminosCheck = new JCheckBox("Aceptar términos y condiciones");
        terminosCheck.setAlignmentX(Component.CENTER_ALIGNMENT);
        terminosCheck.setOpaque(false);
        terminosCheck.setToolTipText("Debes aceptar los términos");
        panel.add(terminosCheck);
        panel.add(Box.createRigidArea(new Dimension(0, 10)));

        JButton aceptarButton = new JButton("Aceptar");
        JButton limpiarButton = new JButton("Eliminar");

        aceptarButton.setBackground(new Color(0, 153, 0));
        aceptarButton.setForeground(Color.WHITE);
        aceptarButton.setToolTipText("Confirmar acción");

        limpiarButton.setBackground(new Color(204, 0, 0));
        limpiarButton.setForeground(Color.WHITE);
        limpiarButton.setToolTipText("Borrar campos");

        aceptarButton.addActionListener((ActionEvent e) -> {
            String usuario = usuarioField.getText().trim();
            String contraseña = new String(contraseñaField.getPassword()).trim();
            boolean aceptaTerminos = terminosCheck.isSelected();

            if (usuario.isEmpty() && contraseña.isEmpty() && !aceptaTerminos) {
                mostrarAviso("Debes introducir usuario, contraseña y aceptar los términos.");
                return;
            }
            if (usuario.isEmpty() && contraseña.isEmpty()) {
                mostrarAviso("Debes introducir usuario y contraseña.");
                return;
            }
            if (usuario.isEmpty()) {
                mostrarAviso("Debes introducir el usuario.");
                return;
            }
            if (contraseña.isEmpty()) {
                mostrarAviso("Debes introducir la contraseña.");
                return;
            }
            if (!aceptaTerminos) {
                mostrarAviso("Debes aceptar los términos y condiciones.");
                return;
            }

            int nivel = seguridadSlider.getValue();
            simularCarga(barraCarga, () -> {
                JOptionPane.showMessageDialog(panel,
                        "Inicio de sesión exitoso.\nNivel de seguridad seleccionado: " + nivel,
                        "Bienvenido",
                        JOptionPane.INFORMATION_MESSAGE);
            });
        });

        limpiarButton.addActionListener((ActionEvent e) -> {
            usuarioField.setText("");
            contraseñaField.setText("");
            terminosCheck.setSelected(false);
            seguridadSlider.setValue(5);
            edadSpinner.setValue(18);
            simularCarga(barraCarga, null);
        });

        JPanel botonesPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 0));
        botonesPanel.setOpaque(false);
        botonesPanel.add(aceptarButton);
        botonesPanel.add(limpiarButton);
        panel.add(botonesPanel);

        return panel;
    }

    private void simularCarga(JProgressBar barra, Runnable alFinalizar) {
        barra.setValue(0);
        Timer timer = new Timer(50, null);
        timer.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int valor = barra.getValue();
                if (valor < 100) {
                    barra.setValue(valor + 10);
                } else {
                    timer.stop();
                    if (alFinalizar != null) alFinalizar.run();
                    new Timer(1000, ev -> barra.setValue(0)).start();
                }
            }
        });
        timer.start();
    }

    private void mostrarAviso(String mensaje) {
        JOptionPane.showMessageDialog(this,
                mensaje,
                "Campos obligatorios",
                JOptionPane.WARNING_MESSAGE);
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
