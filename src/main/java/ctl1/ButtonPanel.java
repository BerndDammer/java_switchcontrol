package ctl1;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.logging.Logger;

import javax.swing.JPanel;
import javax.swing.JToggleButton;

import ctl1.help.MyGC;

public class ButtonPanel extends BorderPanel
{
    private static final long serialVersionUID = 1L;
    private final Logger logger = Logger.getLogger(ButtonPanel.class.getCanonicalName());

    public ButtonPanel(final IControlChannel controlChannel, IButtonActivator buttonActivator)
    {
        super("Activator");

        setLayout(new GridBagLayout());
        gc.reset();
        gc.fill = GridBagConstraints.NONE;

        for (EButtons ebutton : EButtons.values())
        {
            add(new MidiButton(ebutton, controlChannel, buttonActivator), gc.stepRight());
        }
    }
}
