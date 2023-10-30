package ctl1;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.logging.Logger;

import javax.sound.midi.InvalidMidiDataException;
import javax.sound.midi.MidiDevice;
import javax.sound.midi.MidiSystem;
import javax.sound.midi.MidiUnavailableException;
import javax.sound.midi.Receiver;
import javax.sound.midi.ShortMessage;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import ctl1.help.MyGC;

public class MainFrameOld extends JFrame2 implements Runnable
{
    private static final long serialVersionUID = 1L;
    private final Logger logger = Logger.getLogger(this.getClass().getCanonicalName());
    private final MyGC gc = new MyGC();
    private final JButton[] buttons = new JButton[8];
    private final MidiSelector midiSelector = new MidiSelector();

    private class MidiButton extends JButton implements ActionListener
    {
        private static final long serialVersionUID = 1L;
        private final int index;

        MidiButton(int index)
        {
            this.index = index;
            setText("B" + index);
            addActionListener(this);
        }

        @Override
        public void actionPerformed(ActionEvent e)
        {
            try
            {
                final MidiDevice.Info midiDeviceInfo = midiSelector.getSelectedMidi();
                final MidiDevice midiDevice = MidiSystem.getMidiDevice(midiDeviceInfo);
                midiDevice.open();
                final Receiver receiver = midiDevice.getReceiver();
                receiver.send(new ShortMessage(ShortMessage.NOTE_ON, 1, index + 48, 126), System.currentTimeMillis());
                Thread.sleep(300l);
                receiver.send(new ShortMessage(ShortMessage.NOTE_OFF, 1, index + 48, 126), System.currentTimeMillis());
                midiDevice.close();

            } catch (MidiUnavailableException | InvalidMidiDataException | InterruptedException e1)
            {
                // TODO Auto-generated catch block
                e1.printStackTrace();
            }
        }
    }

    // ---------------------GUI_Elements
    private class MidiSelector extends JComboBox<MidiDevice.Info>
    {
        private static final long serialVersionUID = 1L;

        public MidiSelector()
        {
            MidiDevice.Info[] midiDevices = MidiSystem.getMidiDeviceInfo();
            for (MidiDevice.Info info : midiDevices)
            {
                if (true)
                {
                    addItem(info);
                }
            }
        }

        MidiDevice.Info getSelectedMidi()
        {
            return getItemAt(getSelectedIndex());
        }
    }

    public MainFrameOld()
    {
        logger.info("Starting");
        SwingUtilities.invokeLater(this);
    }

    private class myPanel extends JPanel
    {
        private static final long serialVersionUID = 1L;

        myPanel()
        {
            setLayout(new GridBagLayout());
            // GridBagConstraints gc = new GridBagConstraints();
            gc.reset();

            // row 0 platform selector
            gc.fill = GridBagConstraints.HORIZONTAL;
            add(midiSelector, gc);
            gc.fill = GridBagConstraints.NONE;

            for (int i = 0; i < 8; i++)
            {
                buttons[i] = new MidiButton(i);
                gc.nextRow();
                gc.pushHorizontal();
                add(buttons[i], gc);
                gc.popHorizontal();
            }
            gc.nextRow();
            add( new ButtonPanel(null), gc);
        }
    }

    @Override
    public void run()
    {
        setContentPane(new myPanel());
        setTitle("ctl1");
        setVisible(true);
    }
}
