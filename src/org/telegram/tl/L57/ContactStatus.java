package org.telegram.tl.L57;

import org.telegram.mtproto.ProtocolBuffer;
import org.telegram.tl.TLObject;
import org.telegram.tl.TLVector;
import org.telegram.tl.APIContext;
import org.telegram.tl.L57.*;

public class ContactStatus extends TLContactStatus {

    public static final int ID = 0xd3680c61;

    public int user_id;
    public TLUserStatus status;

    public ContactStatus() {
    }

    public ContactStatus(int user_id, TLUserStatus status) {
        this.user_id = user_id;
        this.status = status;
    }

    @Override
    public void deserialize(ProtocolBuffer buffer) {
        user_id = buffer.readInt();
        status = (TLUserStatus) buffer.readTLObject(APIContext.getInstance());
    }

    @Override
    public ProtocolBuffer serialize() {
        ProtocolBuffer buffer = new ProtocolBuffer(32);
        serializeTo(buffer);
        return buffer;
    }


    @Override
    public void serializeTo(ProtocolBuffer buff) {
        buff.writeInt(getConstructor());
        buff.writeInt(user_id);
        buff.writeTLObject(status);
    }


    public int getConstructor() {
        return ID;
    }
}