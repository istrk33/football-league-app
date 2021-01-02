/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package league.exception;

import java.nio.file.FileSystemException;

/**
 *
 * @author Administrateur
 */
//Point 1: exception du programmeur
public class NoSuchFileException extends FileSystemException{
    //Point 10: ici nous avons le point 10 défini dans le sujet, une exception
    //définie par le programmeur
    public NoSuchFileException(String string) {
        super(string);
    }
    
}
