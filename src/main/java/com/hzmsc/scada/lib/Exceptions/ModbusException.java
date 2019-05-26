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
public class ModbusException extends Exception
{
  /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

public ModbusException()
  {
  }

  public ModbusException( String s )
  {
    super( s );
  }
}


