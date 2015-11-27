/*
 *     This file is part of Telegram Server
 *     Copyright (C) 2015  Aykut Alparslan KOÇ
 *
 *     Telegram Server is free software: you can redistribute it and/or modify
 *     it under the terms of the GNU General Public License as published by
 *     the Free Software Foundation, either version 3 of the License, or
 *     (at your option) any later version.
 *
 *     Telegram Server is distributed in the hope that it will be useful,
 *     but WITHOUT ANY WARRANTY; without even the implied warranty of
 *     MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *     GNU General Public License for more details.
 *
 *     You should have received a copy of the GNU General Public License
 *     along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */

package org.telegram.tl.users;

import org.telegram.api.SessionStore;
import org.telegram.api.TLContext;
import org.telegram.api.TLMethod;
import org.telegram.api.UserStore;
import org.telegram.data.UserModel;
import org.telegram.mtproto.ProtocolBuffer;
import org.telegram.tl.*;
import org.telegram.tl.contacts.*;

public class GetFullUser extends TLObject implements TLMethod {

    public static final int ID = -902781519;

    public TLInputUser id;

    public GetFullUser() {
    }

    public GetFullUser(TLInputUser id){
        this.id = id;
    }

    @Override
    public void deserialize(ProtocolBuffer buffer) {
        id = (TLInputUser) buffer.readTLObject(APIContext.getInstance());
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
        buff.writeTLObject(id);
    }

    public int getConstructor() {
        return ID;
    }

    @Override
    public TLObject execute(TLContext context, long messageId, long reqMessageId) {
        int user_id = 0;
        if (this.id instanceof InputUserContact) {
            user_id = ((InputUserContact) this.id).user_id;
        } else if (this.id instanceof InputUserForeign) {
            user_id = ((InputUserForeign) this.id).user_id;
        } else if (this.id instanceof InputUserSelf) {
            UserModel um = UserStore.getInstance().getUser(SessionStore.getInstance().getSession(context.getSessionId()).phone);
            UserContact uc = new UserContact(um.user_id, um.first_name, um.last_name, um.username,
                    um.access_hash, um.phone, new UserProfilePhotoEmpty(), um.status);
            return new UserFull(uc, new Link(new MyLinkContact(), new ForeignLinkMutual(), uc),
                    new PhotoEmpty(), new PeerNotifySettingsEmpty(), false, uc.first_name, uc.last_name);
        }
        UserModel um = UserStore.getInstance().getUser(user_id);
        if (um != null) {
            UserContact uc = new UserContact(um.user_id, um.first_name, um.last_name, um.username,
                    um.access_hash, um.phone, new UserProfilePhotoEmpty(), um.status);
            return new UserFull(uc, new Link(new MyLinkContact(), new ForeignLinkMutual(), uc),
                    new PhotoEmpty(), new PeerNotifySettingsEmpty(), false, uc.first_name, uc.last_name);
        }
        return null;
    }
}