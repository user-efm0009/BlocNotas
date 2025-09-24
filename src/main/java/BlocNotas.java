import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.Objects;

public class BlocNotas extends JFrame {

    private JTextField usuarioField;
    private JPasswordField contraseñaField;
    private JCheckBox terminosCheck;
    public JPanel panel1;

    public BlocNotas() {
        configurarVentana();
        inicializarComponentes();
    }

    private void configurarVentana() {
        setTitle("Bloc de Notas - Versión 1");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(450, 350);
        setLocationRelativeTo(null);
    }

    private void inicializarComponentes() {
        JPanel panelPrincipal = new JPanel();
        panelPrincipal.setLayout(new BoxLayout(panelPrincipal, BoxLayout.Y_AXIS));
        panelPrincipal.setBorder(BorderFactory.createEmptyBorder(20, 40, 20, 40));

        panelPrincipal.add(crearPanelLogo());
        panelPrincipal.add(Box.createRigidArea(new Dimension(0, 20)));
        panelPrincipal.add(crearPanelCampos());
        panelPrincipal.add(Box.createRigidArea(new Dimension(0, 20)));
        panelPrincipal.add(crearCheckTerminos());
        panelPrincipal.add(Box.createRigidArea(new Dimension(0, 20)));
        panelPrincipal.add(crearPanelBotones());

        add(panelPrincipal);
        setVisible(true);
    }

    private JPanel crearPanelLogo() {
        JPanel logoPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        ImageIcon originalIcon = new ImageIcon(Objects.requireNonNull(getClass().getResource("/logo.png")));
        Image imagenEscalada = originalIcon.getImage().getScaledInstance(24, 24, Image.SCALE_SMOOTH);
        ImageIcon iconoEmoji = new ImageIcon(imagenEscalada);

        JLabel logoLabel = new JLabel("INICIO DE SESIÓN", iconoEmoji, JLabel.LEFT);
        logoLabel.setFont(new Font("SansSerif", Font.BOLD, 24));
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

        contraseñaField = new JPasswordField();
        contraseñaField.setPreferredSize(new Dimension(200, contraseñaField.getPreferredSize().height));

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

    private JCheckBox crearCheckTerminos() {
        terminosCheck = new JCheckBox("Aceptar términos y condiciones");
        terminosCheck.setAlignmentX(Component.CENTER_ALIGNMENT);
        return terminosCheck;
    }

    private JPanel crearPanelBotones() {
        JPanel botonesPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 0));

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

        limpiarButton.addActionListener((ActionEvent e) -> {
            usuarioField.setText("");
            contraseñaField.setText("");
            terminosCheck.setSelected(false);
        });
    }

    private void mostrarAviso(String mensaje) {
        JOptionPane.showMessageDialog(this,
                mensaje,
                "Campos obligatorios",
                JOptionPane.WARNING_MESSAGE);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(BlocNotas::new);
    }
}
