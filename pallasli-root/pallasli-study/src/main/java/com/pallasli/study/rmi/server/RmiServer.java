package com.pallasli.study.rmi.server;

import java.rmi.Naming;
import java.rmi.RMISecurityManager;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.server.UnicastRemoteObject;

import com.pallasli.study.rmi.AddServer;

public class RmiServer {
	/**
	 * 启动 RMI 注册服务并进行对象注册
	 * 
	 * 
	 * 
	 * 
	 * 今天，帮别人看UDAS配置问题时，看到采用的是RMI方式的调用，如：rmi://10.20.134.140:2299/DataServer，
	 * 而且这个端口是被直接硬编码在UDAS的代码中的。既然看到了，就找了点资料了解了一下RMI，简单记录总结一下吧。 1.Java RMI 简介
	 * RMI（Remote Method Invocation），RMI是分布式对象软件包，它简化了在多台计算机上的JAVA应用之间的通信。
	 * 必须在jdk1.1以上，RMI用到的类： java.rmi.Remote 所有可以被远程调用的对象都必须实现该接口
	 * java.rmi.server.UnicastRemoteObject 所有可以被远程调用的对象都必须扩展该类 什么是RMI ？
	 * 远程方法调用是一种计算机之间对象互相调用对方函数，启动对方进程的一种机制，使用这种机制，某一台计算机上的对象在调用另外一台计算机上的方法时，
	 * 使用的程序语法规则和在本地机上对象间的方法调用的语法规则一样。 在看一下wikipedia上的简介吧： TheJava Remote Method
	 * InvocationApplication Programming Interface (API), or Java RMI,is a Java
	 * application programming interface that performs the object-oriented
	 * equivalent of remote procedure calls (RPC). 1. The original
	 * implementation depends on Java Virtual Machine (JVM) class representation
	 * mechanisms and it thus only supports making calls from one JVM to
	 * another. The protocol underlying this Java-only implementation is known
	 * as Java Remote Method Protocol (JRMP). 2. In order to support code
	 * running in a non-JVM context, a CORBA version was later developed. Usage
	 * of the term RMI may denote solely the programming interface or may
	 * signify both the API and JRMP, whereas the term RMI-IIOP (read: RMI over
	 * IIOP) denotes the RMI interface delegating most of the functionality to
	 * the supporting CORBA implementation. The programmers of the original RMI
	 * API generalized the code somewhat to support different implementations,
	 * such as a HTTP transport. Additionally, the ability to pass arguments
	 * "by value" was added to CORBA in order to support the RMI interface.
	 * Still, the RMI-IIOP and JRMP implementations do not have fully identical
	 * interfaces.
	 * 
	 * 2.RMI的优势 这种机制给分布计算的系统设计、编程都带来了极大的方便。只要按照RMI规则设计程序，可以不必再过问在RMI之下的网络细节了，如：
	 * TCP和Socket等等。任意两台计算机之间的通讯完全由RMI负责。调用远程计算机上的对象就像本地对象一样方便。 1、面向对象：
	 * RMI可将完整的对象作为参数和返回值进行传递，而不仅仅是预定义的数据类型。也就是说，可以将类似Java
	 * Hash表这样的复杂类型作为一个参数进行传递。 2、可移动属性： RMI可将属性从客户机移动到服务器，或者从服务器移动到客户机。 3、设计方式：
	 * 对象传递功能使你可以在分布式计算中充分利用面向对象技术的强大功能，如二层和三层结构系统。如果用户能够传递属性，
	 * 那么就可以在自己的解决方案中使用面向对象的设计方式。所有面向对象的设计方式无不依靠不同的属性来发挥功能，如果不能传递完整的对象——包括实现和类型—
	 * —就会失去设计方式上所提供的优点。 4、安全性：
	 * RMI使用Java内置的安全机制保证下载执行程序时用户系统的安全。RMI使用专门为保护系统免遭恶意小程序侵害而设计的安全管理程序。
	 * 5、便于编写和使用 RMI使得Java远程服务程序和访问这些服务程序的Java客户程序的编写工作变得轻松、简单。远程接口实际上就是Java接口。
	 * 为了实现RMI的功能必须创建远程对象任何可以被远程调用的对象必须实现远程接口。但远程
	 * 接口本身并不包含任何方法。因而需要创建一个新的接口来扩展远程接口。
	 * 新接口将包含所有可以远程调用的方法。远程对象必须实现这个新接口，由于新的接口扩展了
	 * 远程接口，实现了新接口，就满足了远程对象对实现远程接口的要求，所实现的每个对象都将 作为远程对象引用。 一个国外的PPT上还还总结到， Java
	 * RMI Advantages: Full object support Cross platform. capabilities Robust
	 * communications Large objects Security for client and servers
	 * Distribution/updates of codes
	 * 
	 * 3.RMI的劣势
	 * 从上面的过程来看，RMI对服务器的IP地址和端口依赖很紧密，但是在开发的时候不知道将来的服务器IP和端口如何，但是客户端程序依赖这个IP和端口。
	 * 这也是RMI的局限性之一。这个问题有两种解决途径：一是通过DNS来解决，二是通过封装将IP暴露到程序代码之外。
	 * RMI的局限性之二是RMI是Java语言的远程调用，两端的程序语言必须是Java实现，对于不同语言间的通讯可以考虑用Web
	 * Service或者公用对象请求代理体系（CORBA）来实现。 一个国外的PPT上也总结到， Java RMI Disadvantages:
	 * Java RMI only supports Java Proprietary protocol by single vendor
	 * Requires RMI-lookup Requires non-standard port
	 * 
	 * 4.RMI与Socket的比较 RMI技术比较socket的网络编程主要有以下几个方面： 第一、RMI是面向对象的，而后者不是。
	 * 第二、RMI是与语言相绑定的。比如当你使用Java
	 * RMI技术的时候，客户端与服务器端都必须使用Java开发。而socket的网络编程是使用独立于开发语言的，甚至独立于平台。
	 * 基于socket的网络编程，客户端与服务器端可以使用不同开发语言和不同的平台。
	 * 第三、从网络协议栈的观点来看，RMI与socket的网络编程处于不同层次上。基于socket的网络编程位于TCP协议之上，而RMI在TCP协议之上
	 * ，又定义了自己的应用协议，其传输层采用的是Java远程方法协议(JRMP)。可见，在网络协议栈上，基于RMI的应用位置更高一些，这也决定了，
	 * 与socket的网络编程相比，RMI会丧失一些灵活性和可控性，但是好处是它带给了应用开发者更多的简洁，方便和易用。比如：如果你用的是RMI,
	 * 你不需要关心消息是怎么序列化的，你只需要像本地方法调用一样，使用RMI。代价是：应用开发者无法很好地控制消息的序列化机制。
	 * 第四、这是最后一点不同，我认为也是比较重要的一点，就是两种方法的性能比较，其往往决定着你将使用那种技术来开发你的应用。
	 * 实验的结果是：RMI与TCP based socket相比，传输相同的有效数据，RMI需要占用更多的网络带宽（protocol
	 * overhead）。从这里，我们可以得出一个一般性的结论：RMI主要是用于远程方法的”调用“（RMI是多么的名符其实:)），其技术内涵强调的是
	 * “调用”，基于此，我能想到的是：移动计算，和远程控制，当你的应用不需要在client与server之间传输大量的数据时，RMI是较好的选择，它简洁
	 * 、易于开发。但是，一旦你的应用需要在client与server之间传输大量的数据，极端的，比如FTP应用，则RMI是不适合的，我们应该使用
	 * socket。 PS: RMI的效率还是很高的，一般情况下会比Hessian更高效，比Web
	 * Service更是高效很多；当然和socket这种东东相比，当然要低效一点了，socket更底层一些啊。RMI的具体实现，
	 * 依然是依赖于底层的Socket编程。
	 * 
	 * 
	 * 
	 * 
	 */
	/**
	 * RMI的编程模型是采用框架和桩的模式，基本原理如下:
	 * 客户请求------>(rmi协议)---------->远程框架(注册机)------------>远程对象----------(处理)----
	 * ----->结果返回到框架--------->客户
	 * 
	 * 1. 服务器端创建远程接口和实现远程接口的类
	 * 
	 * 2. 通过RMIC命令(%java_home%/bin/rmic.exe)来编译远程接口实现类从而生成 stub等文件.
	 * 
	 * 3. 运行 rmiregistry命令(%java_home%/bin/rmiregistry.exe) 来启动RMI远程注册机
	 * 
	 * 4. 向RMI注册机注册远程对象供客户端调用
	 * 
	 * @param argv
	 */
	public static void main(String[] argv) {
		try {
			// 启动RMI注册服务，指定端口为1099 （1099为默认端口）
			// 也可以通过命令 ＄java_home/bin/rmiregistry 1099启动
			// 这里用这种方式避免了再打开一个DOS窗口
			// 而且用命令rmiregistry启动注册服务还必须事先用RMIC生成一个占位程序(stub类)为它所用
			LocateRegistry.createRegistry(8888);

			// 创建远程对象的一个或多个实例，下面是hello对象
			// 可以用不同名字注册不同的实例
			AddServer add = new AddServerImpl();

			// 把hello注册到RMI注册服务器上，命名为Hello
			// Naming.rebind("Hello", add);
			Naming.rebind("rmi://127.0.0.1:8888/Hello", add);
			// 如果要把hello实例注册到另一台启动了RMI注册服务的机器上
			// Naming.rebind("//192.168.1.105:1099/Hello",hello);

			System.out.println("Hello Server is ready.");
		} catch (Exception e) {
			System.out.println("Hello Server failed: " + e);
		}
		try {
			System.setSecurityManager(new RMISecurityManager());
			new MclarenServer().registry("//192.168.0.192:1099/Mclaren");
			System.out.println("服务已经注册..");
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
}

class MclarenServer extends UnicastRemoteObject {
	public MclarenServer() throws RemoteException {
		super();
	}

	public void registry(String name) {
		try {
			System.setSecurityManager(new RMISecurityManager());
			Naming.rebind(name, this);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public String sayHello(String username) {
		return "输出为 : " + username;
	}
}