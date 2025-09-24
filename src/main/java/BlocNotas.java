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

        setTitle("Bloc de Notas - Versión 1");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(450, 350);
        setLocationRelativeTo(null);

        // Panel principal
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBorder(BorderFactory.createEmptyBorder(20, 40, 20, 40));

        // Panel de logo + título estilo emoji
        JPanel logoPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        ImageIcon originalIcon = new ImageIcon(Objects.requireNonNull(getClass().getResource("/logo.png")));
        Image imagenEscalada = originalIcon.getImage().getScaledInstance(24, 24, Image.SCALE_SMOOTH);
        ImageIcon iconoEmoji = new ImageIcon(imagenEscalada);

        JLabel logoLabel = new JLabel("INICIO DE SESIÓN", iconoEmoji, JLabel.LEFT);
        logoLabel.setFont(new Font("SansSerif", Font.BOLD, 24));
        logoPanel.add(logoLabel);
        panel.add(logoPanel);
        panel.add(Box.createRigidArea(new Dimension(0, 20)));

        // Panel de campos alineados
        JPanel camposPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        usuarioField = new JTextField();
        usuarioField.setPreferredSize(new Dimension(200, usuarioField.getPreferredSize().height));

        contraseñaField = new JPasswordField();
        contraseñaField.setPreferredSize(new Dimension(200, contraseñaField.getPreferredSize().height));

        gbc.gridx = 0;
        gbc.gridy = 0;
        camposPanel.add(new JLabel("\uD83D\uDC64 Usuario:"), gbc);

        gbc.gridx = 1;
        camposPanel.add(usuarioField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        camposPanel.add(new JLabel("\uD83D\uDD12 Contraseña:"), gbc);

        gbc.gridx = 1;
        camposPanel.add(contraseñaField, gbc);

        panel.add(camposPanel);
        panel.add(Box.createRigidArea(new Dimension(0, 20)));

        // Checkbox centrado
        terminosCheck = new JCheckBox("Aceptar términos y condiciones");
        terminosCheck.setAlignmentX(Component.CENTER_ALIGNMENT);
        panel.add(terminosCheck);
        panel.add(Box.createRigidArea(new Dimension(0, 20)));

        // Botones
        JPanel botonesPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 0));
        JButton accederButton = new JButton("Aceptar");
        JButton limpiarButton = new JButton("Eliminar");

        // Estilo de botones
        accederButton.setBackground(new Color(0, 153, 0)); // Verde
        accederButton.setForeground(Color.WHITE);
        accederButton.setFocusPainted(false);

        limpiarButton.setBackground(new Color(204, 0, 0)); // Rojo
        limpiarButton.setForeground(Color.WHITE);
        limpiarButton.setFocusPainted(false);

        // Acción del botón limpiar
        limpiarButton.addActionListener((ActionEvent e) -> {

            usuarioField.setText("");
            contraseñaField.setText("");
            terminosCheck.setSelected(false);

        });

        botonesPanel.add(accederButton);
        botonesPanel.add(limpiarButton);
        panel.add(botonesPanel);

        add(panel);
        setVisible(true);
    }

    public static void main(String[] args) {

        SwingUtilities.invokeLater(BlocNotas::new);

    }
}
