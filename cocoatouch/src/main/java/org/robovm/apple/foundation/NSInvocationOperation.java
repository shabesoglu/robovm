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
import org.robovm.apple.coregraphics.*;
import org.robovm.apple.coremedia.*;
import org.robovm.apple.security.*;
/*</imports>*/

/*<javadoc>*/
/**
 * @since Available in iOS 2.0 and later.
 */
/*</javadoc>*/
/*<annotations>*/@Library("Foundation") @NativeClass/*</annotations>*/
/*<visibility>*/public/*</visibility>*/ class /*<name>*/NSInvocationOperation/*</name>*/ 
    extends /*<extends>*/NSOperation/*</extends>*/ 
    /*<implements>*//*</implements>*/ {

    /*<ptr>*/public static class NSInvocationOperationPtr extends Ptr<NSInvocationOperation, NSInvocationOperationPtr> {}/*</ptr>*/
    /*<bind>*/static { ObjCRuntime.bind(NSInvocationOperation.class); }/*</bind>*/
    /*<constants>*//*</constants>*/
    /*<constructors>*/
    public NSInvocationOperation() {}
    protected NSInvocationOperation(SkipInit skipInit) { super(skipInit); }
    public NSInvocationOperation(NSObject target, Selector sel, NSObject arg) { super((SkipInit) null); initObject(initWithTarget$selector$object$(target, sel, arg)); }
    public NSInvocationOperation(NSInvocation inv) { super((SkipInit) null); initObject(initWithInvocation$(inv)); }
    /*</constructors>*/
    /*<properties>*/
    
    /*</properties>*/
    /*<members>*//*</members>*/
    /*<methods>*/
    @Method(selector = "initWithTarget:selector:object:")
    protected native @Pointer long initWithTarget$selector$object$(NSObject target, Selector sel, NSObject arg);
    @Method(selector = "initWithInvocation:")
    protected native @Pointer long initWithInvocation$(NSInvocation inv);
    @Method(selector = "invocation")
    public native NSInvocation invocation();
    @Method(selector = "result")
    public native NSObject result();
    /*</methods>*/
}
