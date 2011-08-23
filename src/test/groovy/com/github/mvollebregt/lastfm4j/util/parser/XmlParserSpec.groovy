package com.github.mvollebregt.lastfm4j.util.parser

import com.github.mvollebregt.lastfm4j.model.Artist
import spock.lang.Specification
import com.github.mvollebregt.lastfm4j.model.Album

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

/**
 *
 *
 * @author Michel Vollebregt
 */
class XmlParserSpec extends Specification {

    def parser = new com.github.mvollebregt.lastfm4j.parser.XmlParser();

    def "parse one artist should return one Artist"() {
        given:
            def xml = """<artist rank="1">
                            <name>Dream Theater</name>
                            <playcount>1337</playcount>
                            <mbid>28503ab7-8bf2-4666-a7bd-2644bfc7cb1d</mbid>
                            <url>http://www.last.fm/music/Dream+Theater</url>
                            <streamable>1</streamable>
                            <image size="small">...</image>
                            <image size="medium">...</image>
                            <image size="large">...</image>
                        </artist>"""
        when:
            def artist = parser.parse(new StringReader(xml));
        then:
            assert match(new Artist(name: "Dream Theater"), artist)
    }

    def "parse two artists should return a list of two Artists"() {
        given:
            def xml = """<root><artist><name>First Artist</name></artist>
                     <artist><name>Second Artist</name></artist></root>"""
        when:
            def list = parser.parse(new StringReader(xml));
        then:
            assert match([new Artist(name: "First Artist"), new Artist(name: "Second Artist")], list)
    }

    def "parse an album should return an album"() {
        given:
            def xml = """<album>
                          <name>Believe</name>
                          <id>2026126</id>
                        </album>"""
        when:
            def album = parser.parse(new StringReader(xml));
        then:
            assert match(new Album(name: "Believe"), album)
    }

    private static match(Collection expectedList, Collection observedList) {
        assert expectedList.size() == observedList.size()
        for (int i = 0; i < expectedList.size(); i++) {
            assert match(expectedList[i], observedList[i])
        }
        return true
    }

    private static match(expected, observed) {
        assert expected.class == observed.class
        expected.properties.each {property, value ->
            if (property != null) {
                assert observed[property] == value
            }
        }
        return true
    }
}