/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.hzmsc.scada.lib.Exceptions;

/**
 *
 * @author Stefan Roßmann
 */
public class CRCCheckFailedException extends ModbusException
{
  /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

public CRCCheckFailedException()
  {
  }

  public CRCCheckFailedException( String s )
  {
    super( s );
  }
}


