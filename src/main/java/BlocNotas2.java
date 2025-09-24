import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.Objects;

public class BlocNotas2 extends JFrame {

    private JTextField usuarioField;
    private JPasswordField contraseñaField;
    private JCheckBox terminosCheck;
    private JRadioButton recordarRadio;
    private JComboBox<String> idiomaCombo;
    public JPanel panel1;

    public BlocNotas2() {
        configurarVentana();
        inicializarComponentes();
    }

    private void configurarVentana() {
        setTitle("Bloc de Notas - Versión 2");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(500, 400);
        setLocationRelativeTo(null);
    }

    private void inicializarComponentes() {
        ImageIcon fondoIcon = new ImageIcon(Objects.requireNonNull(getClass().getResource("/fondodeimagen.jpg")));
        Image fondo = fondoIcon.getImage();
        BackgroundPanel panelPrincipal = new BackgroundPanel(fondo);

        panelPrincipal.add(crearPanelLogo());
        panelPrincipal.add(Box.createRigidArea(new Dimension(0, 20)));
        panelPrincipal.add(crearPanelCampos());
        panelPrincipal.add(Box.createRigidArea(new Dimension(0, 10)));
        panelPrincipal.add(crearComboIdioma());
        panelPrincipal.add(Box.createRigidArea(new Dimension(0, 10)));
        panelPrincipal.add(crearRadioRecordar());
        panelPrincipal.add(Box.createRigidArea(new Dimension(0, 10)));
        panelPrincipal.add(crearCheckTerminos());
        panelPrincipal.add(Box.createRigidArea(new Dimension(0, 20)));
        panelPrincipal.add(crearPanelBotones());

        add(panelPrincipal);
        setVisible(true);
    }

    private JPanel crearPanelLogo() {
        JPanel logoPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        logoPanel.setOpaque(false);

        ImageIcon originalIcon = new ImageIcon(Objects.requireNonNull(getClass().getResource("/logo.png")));
        Image imagenEscalada = originalIcon.getImage().getScaledInstance(24, 24, Image.SCALE_SMOOTH);
        ImageIcon iconoEmoji = new ImageIcon(imagenEscalada);

        JLabel logoLabel = new JLabel("BLOC DE NOTAS", iconoEmoji, JLabel.LEFT);
        logoLabel.setFont(new Font("Sanserif", Font.BOLD, 24));
        logoLabel.setForeground(Color.BLACK);
        logoLabel.setToolTipText("Bienvenido al Bloc de Notas");

        logoPanel.add(logoLabel);
        return logoPanel;
    }

    private JPanel crearPanelCampos() {
        JPanel camposPanel = new JPanel(new GridBagLayout());
        camposPanel.setOpaque(false);

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        usuarioField = new JTextField();
        usuarioField.setPreferredSize(new Dimension(200, usuarioField.getPreferredSize().height));
        usuarioField.setToolTipText("Introduce tu nombre de usuario");

        contraseñaField = new JPasswordField();
        contraseñaField.setPreferredSize(new Dimension(200, contraseñaField.getPreferredSize().height));
        contraseñaField.setToolTipText("Introduce tu contraseña");

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

        return camposPanel;
    }

    private JComboBox<String> crearComboIdioma() {
        idiomaCombo = new JComboBox<>(new String[]{"Español", "Inglés", "Francés"});
        idiomaCombo.setToolTipText("Selecciona tu idioma");
        idiomaCombo.setMaximumSize(new Dimension(200, 25));
        idiomaCombo.setAlignmentX(Component.CENTER_ALIGNMENT);
        return idiomaCombo;
    }

    private JRadioButton crearRadioRecordar() {
        recordarRadio = new JRadioButton("Recordar usuario");
        recordarRadio.setOpaque(false);
        recordarRadio.setAlignmentX(Component.CENTER_ALIGNMENT);
        recordarRadio.setToolTipText("Marca si quieres que se recuerde tu usuario");
        return recordarRadio;
    }

    private JCheckBox crearCheckTerminos() {
        terminosCheck = new JCheckBox("Aceptar términos y condiciones");
        terminosCheck.setAlignmentX(Component.CENTER_ALIGNMENT);
        terminosCheck.setOpaque(false);
        terminosCheck.setToolTipText("Debes aceptar los términos para continuar");
        return terminosCheck;
    }

    private JPanel crearPanelBotones() {
        JPanel botonesPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 0));
        botonesPanel.setOpaque(false);

        JButton accederButton = new JButton("Aceptar");
        JButton limpiarButton = new JButton("Eliminar");

        configurarBotonAcceder(accederButton);
        configurarBotonLimpiar(limpiarButton);

        botonesPanel.add(accederButton);
        botonesPanel.add(limpiarButton);
        return botonesPanel;
    }

    private void configurarBotonAcceder(JButton accederButton) {
        accederButton.setBackground(new Color(0, 153, 0));
        accederButton.setForeground(Color.WHITE);
        accederButton.setFocusPainted(false);
        accederButton.setToolTipText("Haz clic para iniciar sesión");

        accederButton.addActionListener((ActionEvent e) -> {
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

            JOptionPane.showMessageDialog(this,
                    "Inicio de sesión exitoso.",
                    "Bienvenido",
                    JOptionPane.INFORMATION_MESSAGE);
        });
    }

    private void configurarBotonLimpiar(JButton limpiarButton) {
        limpiarButton.setBackground(new Color(204, 0, 0));
        limpiarButton.setForeground(Color.WHITE);
        limpiarButton.setFocusPainted(false);
        limpiarButton.setToolTipText("Haz clic para borrar los campos");

        limpiarButton.addActionListener((ActionEvent e) -> {
            usuarioField.setText("");
            contraseñaField.setText("");
            terminosCheck.setSelected(false);
            recordarRadio.setSelected(false);
            idiomaCombo.setSelectedIndex(0);
        });
    }

    private void mostrarAviso(String mensaje) {
        JOptionPane.showMessageDialog(this,
                mensaje,
                "Campos obligatorios",
                JOptionPane.WARNING_MESSAGE);
    }

    // Panel personalizado para fondo
    static class BackgroundPanel extends JPanel {
        private final Image backgroundImage;

        public BackgroundPanel(Image image) {
            this.backgroundImage = image;
            setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
            setBorder(BorderFactory.createEmptyBorder(20, 40, 20, 40));
        }

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(BlocNotas2::new);
    }
}
