Inventory client:

fallback method:
    return false -> api POST /api/order used to throw 500 internal server error
                    circuit didn't used to get broken

    throw exception ->  api POST /api/order used to throws exception
                        circuit breaks
                        throws 404 not found ( when order service is up
                                                and inventory service is down)
                        any /api/inventory api -> 503 service unavailable


    remove @Retry ->    503 service unavailable thrown from 
                        @controller advice global exception handler

                        circuit not breaking, lol.

    retry max-attempts=1 -> same as remove @Retry
                
                        