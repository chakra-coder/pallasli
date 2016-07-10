package com.pallasli.study.nio;

import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * 
 * Java NIO的通道类似流，但又有些不同：
 * 
 * 既可以从通道中读取数据，又可以写数据到通道。但流的读写通常是单向的。 通道可以异步地读写。
 * 通道中的数据总是要先读到一个Buffer，或者总是要从一个Buffer中写入。 正如上面所说，从通道读取数据到缓冲区，从缓冲区写入数据到通道。如下图所示：
 * 
 * 
 * 
 * 
 * Channel的实现
 * 
 * 这些是Java NIO中最重要的通道的实现：
 * 
 * FileChannel DatagramChannel SocketChannel ServerSocketChannel FileChannel
 * 从文件中读写数据。
 * 
 * DatagramChannel 能通过UDP读写网络中的数据。
 * 
 * SocketChannel 能通过TCP读写网络中的数据。
 * 
 * ServerSocketChannel可以监听新进来的TCP连接，像Web服务器那样。对每一个新进来的连接都会创建一个SocketChannel。
 * 
 * 注意 buf.flip()
 * 的调用，首先读取数据到Buffer，然后反转Buffer,接着再从Buffer中读取数据。下一节会深入讲解Buffer的更多细节。
 * 
 * 
 * 
 * 
 * @author lyt1987
 *
 */
public class FileChannelTest {
	public static void main(String[] args) throws Exception {

		//
		// Java NIO中的FileChannel是一个连接到文件的通道。可以通过文件通道读写文件。
		//
		// FileChannel无法设置为非阻塞模式，它总是运行在阻塞模式下。
		//
		//
		// 打开FileChannel
		//
		// 在使用FileChannel之前，必须先打开它。但是，我们无法直接打开一个FileChannel，需要通过使用一个InputStream、OutputStream或RandomAccessFile来获取一个FileChannel实例。下面是通过RandomAccessFile打开FileChannel的示例：
		//
		// RandomAccessFile aFile = new RandomAccessFile("data/nio-data.txt",
		// "rw");
		// FileChannel inChannel = aFile.getChannel();
		// 从FileChannel读取数据
		//
		// 调用多个read()方法之一从FileChannel中读取数据。如：
		//
		// ByteBuffer buf = ByteBuffer.allocate(48);
		// int bytesRead = inChannel.read(buf);
		// 首先，分配一个Buffer。从FileChannel中读取的数据将被读到Buffer中。
		//
		// 然后，调用FileChannel.read()方法。该方法将数据从FileChannel读取到Buffer中。read()方法返回的int值表示了有多少字节被读到了Buffer中。如果返回-1，表示到了文件末尾。
		//
		// 向FileChannel写数据
		//
		// 使用FileChannel.write()方法向FileChannel写数据，该方法的参数是一个Buffer。如：
		//
		// String newData = "New String to write to file..." +
		// System.currentTimeMillis();
		//
		// ByteBuffer buf = ByteBuffer.allocate(48);
		// buf.clear();
		// buf.put(newData.getBytes());
		//
		// buf.flip();
		//
		// while(buf.hasRemaining()) {
		// channel.write(buf);
		// }
		// 注意FileChannel.write()是在while循环中调用的。因为无法保证write()方法一次能向FileChannel写入多少字节，因此需要重复调用write()方法，直到Buffer中已经没有尚未写入通道的字节。
		//
		// 关闭FileChannel
		//
		// 用完FileChannel后必须将其关闭。如：
		//
		// channel.close();
		// FileChannel的position方法
		//
		// 有时可能需要在FileChannel的某个特定位置进行数据的读/写操作。可以通过调用position()方法获取FileChannel的当前位置。
		//
		// 也可以通过调用position(long pos)方法设置FileChannel的当前位置。
		//
		// 这里有两个例子:
		//
		// long pos = channel.position();
		// channel.position(pos +123);
		// 如果将位置设置在文件结束符之后，然后试图从文件通道中读取数据，读方法将返回-1 —— 文件结束标志。
		//
		// 如果将位置设置在文件结束符之后，然后向通道中写数据，文件将撑大到当前位置并写入数据。这可能导致“文件空洞”，磁盘上物理文件中写入的数据间有空隙。
		//
		// FileChannel的size方法
		//
		// FileChannel实例的size()方法将返回该实例所关联文件的大小。如:
		//
		// long fileSize = channel.size();
		// FileChannel的truncate方法
		//
		// 可以使用FileChannel.truncate()方法截取一个文件。截取文件时，文件将中指定长度后面的部分将被删除。如：
		//
		// channel.truncate(1024);
		// 这个例子截取文件的前1024个字节。
		//
		// FileChannel的force方法
		//
		// FileChannel.force()方法将通道里尚未写入磁盘的数据强制写到磁盘上。出于性能方面的考虑，操作系统会将数据缓存在内存中，所以无法保证写入到FileChannel里的数据一定会即时写到磁盘上。要保证这一点，需要调用force()方法。
		//
		// force()方法有一个boolean类型的参数，指明是否同时将文件元数据（权限信息等）写到磁盘上。
		//
		// 下面的例子同时将文件数据和元数据强制写到磁盘上：
		//
		// channel.force(true);
		//

		RandomAccessFile aFile = new RandomAccessFile("data/nio-data.txt", "rw");
		FileChannel inChannel = aFile.getChannel();

		ByteBuffer buf = ByteBuffer.allocate(48);

		int bytesRead = inChannel.read(buf);
		while (bytesRead != -1) {

			System.out.println("Read " + bytesRead);
			buf.flip();

			while (buf.hasRemaining()) {
				System.out.print((char) buf.get());
			}

			buf.clear();
			bytesRead = inChannel.read(buf);
		}
		aFile.close();

	}
}
