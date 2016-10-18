package org.telegram.tl.L57.messages;

import org.telegram.mtproto.ProtocolBuffer;
import org.telegram.tl.TLObject;
import org.telegram.tl.TLVector;
import org.telegram.tl.APIContext;
import org.telegram.tl.L57.*;

public class ReorderStickerSets extends TLObject {

    public static final int ID = 0x78337739;

    public int flags;
    public TLVector<Long> order;

    public ReorderStickerSets() {
        this.order = new TLVector<>();
    }

    public ReorderStickerSets(int flags, TLVector<Long> order) {
        this.flags = flags;
        this.order = order;
    }

    @Override
    public void deserialize(ProtocolBuffer buffer) {
        flags = buffer.readInt();
        order = (TLVector<Long>) buffer.readTLObject(APIContext.getInstance());
    }

    @Override
    public ProtocolBuffer serialize() {
        ProtocolBuffer buffer = new ProtocolBuffer(32);
        setFlags();
        serializeTo(buffer);
        return buffer;
    }

    public void setFlags() {
    }

    @Override
    public void serializeTo(ProtocolBuffer buff) {
        buff.writeInt(getConstructor());
        buff.writeInt(flags);
        buff.writeTLObject(order);
    }

    public boolean is_masks() {
        return (flags & (1 << 0)) != 0;
    }

    public void set_masks(boolean v) {
        if (v) {
            flags |= (1 << 0);
        } else {
            flags &= ~(1 << 0);
        }
    }

    public int getConstructor() {
        return ID;
    }
}