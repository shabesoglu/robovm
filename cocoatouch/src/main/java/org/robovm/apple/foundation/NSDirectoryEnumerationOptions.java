/*
 * Copyright (C) 2014 Trillian Mobile AB
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.robovm.apple.foundation;

/*<imports>*/
import java.io.*;
import java.nio.*;
import java.util.*;
import org.robovm.objc.*;
import org.robovm.objc.annotation.*;
import org.robovm.objc.block.*;
import org.robovm.rt.*;
import org.robovm.rt.bro.*;
import org.robovm.rt.bro.annotation.*;
import org.robovm.rt.bro.ptr.*;
import org.robovm.apple.corefoundation.*;
import org.robovm.apple.uikit.*;
import org.robovm.apple.coreanimation.*;
import org.robovm.apple.coredata.*;
import org.robovm.apple.coregraphics.*;
import org.robovm.apple.coremedia.*;
import org.robovm.apple.security.*;
import org.robovm.apple.dispatch.*;
/*</imports>*/

/*<javadoc>*/
/**
 * @since Available in iOS 4.0 and later.
 */
/*</javadoc>*/
/*<annotations>*/@Marshaler(Bits.AsMachineSizedIntMarshaler.class)/*</annotations>*/
public final class /*<name>*/NSDirectoryEnumerationOptions/*</name>*/ extends Bits</*<name>*/NSDirectoryEnumerationOptions/*</name>*/> {
    /*<values>*/
    public static final NSDirectoryEnumerationOptions None = new NSDirectoryEnumerationOptions(0L);
    public static final NSDirectoryEnumerationOptions SubdirectoryDescendants = new NSDirectoryEnumerationOptions(1L);
    public static final NSDirectoryEnumerationOptions PackageDescendants = new NSDirectoryEnumerationOptions(2L);
    public static final NSDirectoryEnumerationOptions HiddenFiles = new NSDirectoryEnumerationOptions(4L);
    /*</values>*/

    private static final /*<name>*/NSDirectoryEnumerationOptions/*</name>*/[] values = _values(/*<name>*/NSDirectoryEnumerationOptions/*</name>*/.class);

    public /*<name>*/NSDirectoryEnumerationOptions/*</name>*/(long value) { super(value); }
    private /*<name>*/NSDirectoryEnumerationOptions/*</name>*/(long value, long mask) { super(value, mask); }
    protected /*<name>*/NSDirectoryEnumerationOptions/*</name>*/ wrap(long value, long mask) {
        return new /*<name>*/NSDirectoryEnumerationOptions/*</name>*/(value, mask);
    }
    protected /*<name>*/NSDirectoryEnumerationOptions/*</name>*/[] _values() {
        return values;
    }
    public static /*<name>*/NSDirectoryEnumerationOptions/*</name>*/[] values() {
        return values.clone();
    }
}
