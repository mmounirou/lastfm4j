package com.github.mvollebregt.lastfm4j;

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
 * @author Michel Vollebregt
 */
public enum Period {

    OVERALL("overall"),
    _7DAY("7day"),
    _3MONTH("3month"),
    _6MONTH("6month"),
    _12MONTH("12month");
    
    private String m_val;

	private Period(String strVal)
	{
		m_val = strVal;
	}

	public static Period getByName(String strVal)
	{
		for ( Period period : values() )
		{
			if(period.m_val.equalsIgnoreCase(strVal))
			{
				return period;
			}
		}
		return null;
	}

	public String getName()
	{	return m_val;
	}
	
	@Override
	public String toString()
	{
		return m_val;
	}
}
