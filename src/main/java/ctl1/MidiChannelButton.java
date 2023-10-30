package ctl1;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.logging.Logger;

import javax.sound.midi.MidiDevice;
import javax.swing.ButtonGroup;
import javax.swing.JToggleButton;

class MidiChannelButton extends JToggleButton implements ActionListener
{
    private static final Logger logger = Logger.getLogger(JToggleButton.class.getCanonicalName());

    private static final long serialVersionUID = 1L;
//    private final EButtons midiCode;
    final MidiDevice.Info info;
//    private final IButtonActivator buttonActivator;
//    private final IControlChannel controlChannel;
    private final ButtonGroup group;

    MidiChannelButton(final MidiDevice.Info info, final ButtonGroup group)
    {
//        this.midiCode = midiCode;
//        this.buttonActivator = buttonActivator;
//        this.controlChannel = controlChannel;
        this.info = info;
        this.group = group;
        setText(info.getName());
        addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e)
    {
        if( group.isSelected(this.getModel()) )
        {
            group.clearSelection();
        }
        logger.info("" + isSelected());
    }
}
