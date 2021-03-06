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

package org.telegram.data;

import java.io.Serializable;

/**
 * Created by aykut on 04/10/16.
 */
public class SecretChatModel implements Serializable {
    public int chat_id;
    public int admin_id;
    public int participant_id;

    public SecretChatModel(int chat_id, int admin_id, int participant_id) {
        this.chat_id = chat_id;
        this.admin_id = admin_id;
        this.participant_id = participant_id;
    }
}
