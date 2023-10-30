package ctl1;

import javax.sound.midi.MidiDevice;
import javax.sound.midi.MidiSystem;
import javax.sound.midi.Receiver;
import javax.swing.JComboBox;

class MidiSelector extends JComboBox<MidiDevice.Info> implements IControlChannel
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

//    MidiDevice.Info getSelectedMidi()
//    {
//        return getItemAt(getSelectedIndex());
//    }

    @Override
//    public MidiDevice.Info getControl()
    public Receiver getControl()
    {
//        return getItemAt(getSelectedIndex());
        return null;
    }
}
