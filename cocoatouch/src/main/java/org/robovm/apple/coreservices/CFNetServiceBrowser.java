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
package org.robovm.apple.coreservices;

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
import org.robovm.apple.foundation.*;
import org.robovm.apple.corefoundation.*;
/*</imports>*/

/*<javadoc>*/
/*</javadoc>*/
/*<annotations>*/@Library("CFNetwork")/*</annotations>*/
/*<visibility>*/public/*</visibility>*/ class /*<name>*/CFNetServiceBrowser/*</name>*/ 
    extends /*<extends>*/CFType/*</extends>*/ 
    /*<implements>*//*</implements>*/ {

    /*<ptr>*/public static class CFNetServiceBrowserPtr extends Ptr<CFNetServiceBrowser, CFNetServiceBrowserPtr> {}/*</ptr>*/
    
    public interface ClientCallback {
        void invoke(CFNetServiceBrowser browser, CFNetServiceBrowserFlags flags, CFType domainOrService, CFStreamError error);
    }
  
    private static java.util.concurrent.atomic.AtomicLong refconId = new java.util.concurrent.atomic.AtomicLong();
    private long localRefconId;
    private static Map<Long, ClientCallback> callbacks = new HashMap<Long, ClientCallback>();
    private static final java.lang.reflect.Method cbInvoke;
    
    static {
        try {
            cbInvoke = CFNetServiceBrowser.class.getDeclaredMethod("cbInvoke", CFNetServiceBrowser.class, CFNetServiceBrowserFlags.class, CFType.class, CFStreamError.CFStreamErrorPtr.class, long.class);
        } catch (Throwable e) {
            throw new Error(e);
        }
    }
    /*<bind>*/static { Bro.bind(CFNetServiceBrowser.class); }/*</bind>*/
    /*<constants>*//*</constants>*/
    /*<constructors>*/
    protected CFNetServiceBrowser() {}
    /*</constructors>*/
    /*<properties>*//*</properties>*/
    /*<members>*//*</members>*/
    @Callback
    private static void cbInvoke(CFNetServiceBrowser browser, CFNetServiceBrowserFlags flags, CFType domainOrService, CFStreamError.CFStreamErrorPtr error, @Pointer long refcon) {
        ClientCallback callback = null;
        synchronized (callbacks) {
            callback = callbacks.get(refcon);
        }
        CFStreamError err = null;
        if (error != null) err = error.get();
        callback.invoke(browser, flags, domainOrService, err);
    }
    /**
     * @since Available in iOS 2.0 and later.
     */
    public static CFNetServiceBrowser create(ClientCallback clientCB) {
        long refconId = CFNetServiceBrowser.refconId.getAndIncrement();
        CFNetServiceClientContext context = new CFNetServiceClientContext();
        context.setInfo(refconId);
        synchronized (callbacks) {
            callbacks.put(refconId, clientCB);
        }
        CFNetServiceBrowser service = create(null, new FunctionPtr(cbInvoke), context);
        if (service != null) service.localRefconId = refconId;
        return service;
    }
    /**
     * @since Available in iOS 2.0 and later.
     */
    public boolean searchForDomains(boolean registrationDomains) {
        return searchForDomains(registrationDomains, null);
    }
    /**
     * @since Available in iOS 2.0 and later.
     */
    public boolean searchForServices(String domain, String serviceType) {
        return searchForServices(domain, serviceType, null);
    }
    /**
     * @since Available in iOS 2.0 and later.
     */
    public void stopSearch() {
        stopSearch(null);
    }
    /*<methods>*/
    /**
     * @since Available in iOS 2.0 and later.
     */
    @Bridge(symbol="CFNetServiceBrowserGetTypeID", optional=true)
    public static native @MachineSizedUInt long getClassTypeID();
    /**
     * @since Available in iOS 2.0 and later.
     */
    @Bridge(symbol="CFNetServiceBrowserCreate", optional=true)
    protected static native CFNetServiceBrowser create(CFAllocator alloc, FunctionPtr clientCB, CFNetServiceClientContext clientContext);
    /**
     * @since Available in iOS 2.0 and later.
     */
    @Bridge(symbol="CFNetServiceBrowserInvalidate", optional=true)
    public native void invalidate();
    /**
     * @since Available in iOS 2.0 and later.
     */
    @Bridge(symbol="CFNetServiceBrowserSearchForDomains", optional=true)
    protected native boolean searchForDomains(boolean registrationDomains, CFStreamError.CFStreamErrorPtr error);
    /**
     * @since Available in iOS 2.0 and later.
     */
    @Bridge(symbol="CFNetServiceBrowserSearchForServices", optional=true)
    protected native boolean searchForServices(String domain, String serviceType, CFStreamError.CFStreamErrorPtr error);
    /**
     * @since Available in iOS 2.0 and later.
     */
    @Bridge(symbol="CFNetServiceBrowserStopSearch", optional=true)
    protected native void stopSearch(CFStreamError.CFStreamErrorPtr error);
    /**
     * @since Available in iOS 2.0 and later.
     */
    @Bridge(symbol="CFNetServiceBrowserScheduleWithRunLoop", optional=true)
    public native void schedule(CFRunLoop runLoop, CFString runLoopMode);
    /**
     * @since Available in iOS 2.0 and later.
     */
    @Bridge(symbol="CFNetServiceBrowserUnscheduleFromRunLoop", optional=true)
    public native void unschedule(CFRunLoop runLoop, CFString runLoopMode);
    /*</methods>*/
}
