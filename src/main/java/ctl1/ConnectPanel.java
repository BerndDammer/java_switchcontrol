package ctl1;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.util.logging.Logger;

import javax.sound.midi.MidiDevice;
import javax.sound.midi.MidiSystem;
import javax.sound.midi.MidiUnavailableException;
import javax.sound.midi.Receiver;
import javax.swing.ButtonGroup;
import javax.swing.JLabel;

import ctl1.help.MyGC;

public class ConnectPanel extends BorderPanel implements IControlChannel
{
    private static final long serialVersionUID = 1L;
    private final Logger logger = Logger.getLogger(ConnectPanel.class.getCanonicalName());

//    private final MidiSelector midiSelector = new MidiSelector();

    public ConnectPanel()
    {
        super("Connections");
        final MyGC gc = new MyGC();

        setLayout(new GridBagLayout());
        gc.reset();
        gc.fill = GridBagConstraints.HORIZONTAL;

        add(new JLabel("Audio", JLabel.CENTER), gc);
        gc.nextRow();

        add(new JLabel("Midi", JLabel.CENTER), gc);
        gc.nextRow();

//        add(midiSelector, gc);
        final ButtonGroup midiButtons = new ButtonGroup();
        // one button for each possible midi device
        final MidiDevice.Info[] midiDevices = MidiSystem.getMidiDeviceInfo();
        for (MidiDevice.Info info : midiDevices)
        {
            // try if midi device has output channel
            MidiDevice midiDevice = null;
            try
            {
                midiDevice = MidiSystem.getMidiDevice(info);
                midiDevice.open();
                final Receiver receiver = midiDevice.getReceiver();
                if(receiver != null)
                {
                    final MidiChannelButton button = new MidiChannelButton(info,midiButtons);
                    midiButtons.add(button);
                    add( button, gc);
                    gc.nextRow();
                }
                
            } catch (MidiUnavailableException e)
            {
                logger.info(e.getMessage());
            } finally
            {
                midiDevice.close();
            }
//            if (true)
//            {
//                addItem(info);
//            }
        }
    }

    @Override
    public Receiver getControl()
    {
//        return midiSelector.getControl();
        return null;
    }
}
