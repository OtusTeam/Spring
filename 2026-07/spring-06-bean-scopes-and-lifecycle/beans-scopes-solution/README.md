#### Решение к упражнению №1

@Scope("singleton")  
SingletonGreetingServiceImpl

@Scope("prototype")  
PrototypeGreetingServiceImpl

@Scope(value = "request", proxyMode = ScopedProxyMode.INTERFACES)
RequestGreetingServiceImpl

@Scope(value = "session", proxyMode = ScopedProxyMode.INTERFACES)
SessionGreetingServiceImpl


