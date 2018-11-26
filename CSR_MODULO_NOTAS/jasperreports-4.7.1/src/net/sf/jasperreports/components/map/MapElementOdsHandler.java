/*
 * JasperReports - Free Java Reporting Library.
 * Copyright (C) 2001 - 2011 Jaspersoft Corporation. All rights reserved.
 * http://www.jaspersoft.com
 *
 * Unless you have purchased a commercial license agreement from Jaspersoft,
 * the following license terms apply:
 *
 * This program is part of JasperReports.
 *
 * JasperReports is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * JasperReports is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with JasperReports. If not, see <http://www.gnu.org/licenses/>.
 */
package net.sf.jasperreports.components.map;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRGenericPrintElement;
import net.sf.jasperreports.engine.JRPrintImage;
import net.sf.jasperreports.engine.export.JRExporterGridCell;
import net.sf.jasperreports.engine.export.oasis.GenericElementOdsHandler;
import net.sf.jasperreports.engine.export.oasis.JROdsExporter;
import net.sf.jasperreports.engine.export.oasis.JROdsExporterContext;

/**
 * @author sanda zaharia (shertage@users.sourceforge.net)
 * @version $Id: MapElementOdsHandler.java 5594 2012-08-21 10:38:06Z narcism $
 */
public class MapElementOdsHandler implements GenericElementOdsHandler
{
	private static final MapElementOdsHandler INSTANCE = new MapElementOdsHandler();
	
	public static MapElementOdsHandler getInstance()
	{
		return INSTANCE;
	}
	
	public void exportElement(
		JROdsExporterContext exporterContext,
		JRGenericPrintElement element,
		JRExporterGridCell gridCell
		)
	{
		try
		{
			JROdsExporter exporter = (JROdsExporter)exporterContext.getExporter();
			exporter.exportImage(exporterContext.getTableBuilder(), getImage(exporterContext, element), gridCell);
		}
		catch (Exception e)
		{
			throw new RuntimeException(e);
		}
	}
	
	@Override
	public JRPrintImage getImage(JROdsExporterContext exporterContext,
			JRGenericPrintElement element) throws JRException {
		return MapElementImageProvider.getImage(exporterContext.getJasperReportsContext(), element);
	}

	public boolean toExport(JRGenericPrintElement element) {
		return true;
	}

}
