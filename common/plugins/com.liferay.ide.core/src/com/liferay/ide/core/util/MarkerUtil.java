/*******************************************************************************
 * Copyright (c) 2000-present Liferay, Inc. All rights reserved.
 *
 * This library is free software; you can redistribute it and/or modify it under
 * the terms of the GNU Lesser General Public License as published by the Free
 * Software Foundation; either version 2.1 of the License, or (at your option)
 * any later version.
 *
 * This library is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License for more
 * details.
 *
 *******************************************************************************/
package com.liferay.ide.core.util;

import com.liferay.ide.core.LiferayCore;

import org.eclipse.core.resources.IMarker;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.CoreException;


/**
 * @author Gregory Amerson
 */
public class MarkerUtil
{
    public static void clearMarkers( IResource resource, final String makerType, final String sourceId )
    {
        try
        {
            if( resource.isAccessible() )
            {
                final IMarker[] markers = resource.findMarkers( makerType, true, IResource.DEPTH_INFINITE );

                for( IMarker marker : markers )
                {
                    if( marker.getAttribute( IMarker.SOURCE_ID ).equals( sourceId ) )
                    {
                        marker.delete();
                    }
                }
            }
        }
        catch( CoreException e )
        {
            LiferayCore.logError( e );
        }
    }

    public static IMarker[] findMarkers( IResource resource, final String makerType, final String sourceId )
    {
        try
        {
            if( resource.isAccessible() )
            {
                return resource.findMarkers( makerType, true, IResource.DEPTH_INFINITE );
            }
        }
        catch( CoreException e )
        {
            LiferayCore.logError( e );
        }

        return null;
    }

    public static void setMarker(
        IResource resource, String markerType, int markerSeverity, String markerMsg, String markerLocation,
        String markerSourceId ) throws CoreException
    {
        final IMarker marker = resource.createMarker( markerType );

        marker.setAttribute( IMarker.SEVERITY, markerSeverity );
        marker.setAttribute( IMarker.MESSAGE, markerMsg );
        marker.setAttribute( IMarker.LOCATION, markerLocation );
        marker.setAttribute( IMarker.SOURCE_ID, markerSourceId );
    }
}