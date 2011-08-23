package com.github.mvollebregt.lastfm4j.parser;

// This file is part of SpotifyDiscoverer.
//
// SpotifyDiscoverer is free software: you can redistribute it and/or modify
// it under the terms of the GNU General Public License as published by
// the Free Software Foundation, either version 3 of the License, or
// (at your option) any later version.
//
// SpotifyDiscoverer is distributed in the hope that it will be useful,
// but WITHOUT ANY WARRANTY; without even the implied warranty of
// MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
// GNU General Public License for more details.
//
// You should have received a copy of the GNU General Public License
// along with SpotifyDiscoverer.  If not, see <http://www.gnu.org/licenses/>.

import com.github.mvollebregt.lastfm4j.model.Album;
import com.github.mvollebregt.lastfm4j.model.Artist;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Michel Vollebregt
 */
public class ArtistHandler extends DefaultHandler {

    private Object objectTree;
    private Object objectInProgress;

    private StringBuilder characterBuffer;

    public Object getObjectTree() {
        return objectTree;
    }

    @Override
    public void startElement(String uri, String name, String qname, Attributes attributes) throws SAXException {
        if ("artist".equals(qname)) {
            objectInProgress = new Artist();
        } else if ("album".equals(qname)) {
            objectInProgress = new Album();
        } else if (objectInProgress == null) {
            objectTree = new ArrayList();
        }
        characterBuffer = null;
    }

    @Override
    public void endElement(String uri, String name, String qname) throws SAXException {
        if (objectInProgress instanceof Artist) {
            if ("name".equals(qname)) {
                ((Artist) objectInProgress).setName(characterBuffer.toString());
            }
        } else if (objectInProgress instanceof Album) {
            if ("name".equals(qname)) {
                ((Album) objectInProgress).setName(characterBuffer.toString());
            }
        }
        if ("artist".equals(qname) || "album".equals(qname)) {
            if (objectTree != null) {
                ((List) objectTree).add(objectInProgress);
            } else {
                objectTree = objectInProgress;
            }
        }
    }

    public void characters(char[] ch, int start, int length) throws SAXException {
        if (characterBuffer == null) characterBuffer = new StringBuilder();
        characterBuffer.append(ch, start, length);
    }
}