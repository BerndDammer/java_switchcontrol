package ctl1;

import java.util.logging.Logger;

import javax.sound.midi.InvalidMidiDataException;
import javax.sound.midi.MidiDevice;
import javax.sound.midi.MidiSystem;
import javax.sound.midi.MidiUnavailableException;
import javax.sound.midi.Receiver;
import javax.sound.midi.ShortMessage;

public interface IButtonActivatorOld
{
    static final Logger logger = Logger.getLogger(IButtonActivatorOld.class.getCanonicalName());

    default void activeButton(EButtons button, boolean enable, IControlChannel channel)
    {
        try
        {
            final ShortMessage shortMessage = new ShortMessage( //
                    enable ? ShortMessage.NOTE_ON : ShortMessage.NOTE_OFF, //
                    1, button.getMidiCode(), //
                    enable ? 127 : 0);

            final MidiDevice.Info midiDeviceInfo = channel.getControl();
            final MidiDevice midiDevice = MidiSystem.getMidiDevice(midiDeviceInfo);
            midiDevice.open();
            final Receiver receiver = midiDevice.getReceiver();
            receiver.send(shortMessage, System.currentTimeMillis());
            midiDevice.close();

        } catch (InvalidMidiDataException | MidiUnavailableException e)
        {
            logger.warning(e.getMessage());
        }
    }
}
