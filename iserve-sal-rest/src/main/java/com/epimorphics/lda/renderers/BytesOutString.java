/*
    See lda-top/LICENCE (or http://elda.googlecode.com/hg/LICENCE)
    for the licence for this software.
    
    (c) Copyright 2011 Epimorphics Limited
    $Id$
*/

package com.epimorphics.lda.renderers;

import com.epimorphics.lda.renderers.Renderer.BytesOut;
import com.epimorphics.lda.support.Times;
import com.hp.hpl.jena.shared.WrappedException;

import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;

/**
 * A BytesOutString writes string content, UTF-8 encoded, to
 * the supplied stream.
 *
 * @author chris
 */
public class BytesOutString implements BytesOut {

    final String content;

    public BytesOutString(String content) {
        this.content = content;
    }

    @Override
    public void writeAll(Times t, OutputStream os) {
        try {
            OutputStream bos = new BufferedOutputStream(os);
            OutputStreamWriter osw = new OutputStreamWriter(bos, "UTF-8");
            osw.write(content);
            osw.flush();
            osw.close();
        } catch (IOException e) {
            throw new WrappedException(e);
        }
    }

}