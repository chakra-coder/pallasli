package com.pallasli.study.zookeeper;

import java.util.Arrays;
import java.util.List;

import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.ZooDefs.Ids;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * TODO
 * 
 * @author cuiran
 * @version TODO
 */
public class ZooKeeperOperator extends AbstractZooKeeper {

	private static Logger log = LoggerFactory.getLogger(ZooKeeperOperator.class.getName());

	/**
	 * 
	 * <b>function:</b>创建持久态的znode,比支持多层创建.比如在创建/parent/child的情况下,无/parent.无法通过
	 * 
	 * @author cuiran
	 * @createDate 2013-01-16 15:08:38
	 * @param path
	 * @param data
	 * @throws KeeperException
	 * @throws InterruptedException
	 */
	public void create(String path, byte[] data) throws KeeperException, InterruptedException {
		/**
		 * 此处采用的是CreateMode是PERSISTENT 表示The znode will not be automatically
		 * deleted upon client's disconnect. EPHEMERAL 表示The znode will be
		 * deleted upon the client's disconnect.
		 */

		if (this.zooKeeper.exists(path, this) == null) {
			this.zooKeeper.create(path, data, Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);
		}
	}

	/**
	 * 
	 * <b>function:</b>获取节点信息
	 * 
	 * @param path
	 * @throws KeeperException
	 * @throws InterruptedException
	 */
	public void getChild(String path) throws KeeperException, InterruptedException {
		try {
			List<String> list = this.zooKeeper.getChildren(path, false);
			if (list.isEmpty()) {
				log.debug(path + "中没有节点");
				System.out.println(path + "中没有节点");
			} else {
				log.debug(path + "中存在节点");
				System.out.println(path + "中存在节点");
				for (String child : list) {
					log.debug("节点为：" + child);
					System.out.println("节点为：" + child);
				}
			}
		} catch (KeeperException.NoNodeException e) {
			throw e;

		}
	}

	public byte[] getData(String path) throws KeeperException, InterruptedException {
		return this.zooKeeper.getData(path, false, null);
	}

	public static void main(String[] args) {
		try {
			ZooKeeperOperator zkoperator = new ZooKeeperOperator();
			zkoperator.connect("127.0.0.1");

			byte[] data = new byte[] { 'a', 'b', 'c', 'd' };

			zkoperator.create("/root", null);
			System.out.println(Arrays.toString(zkoperator.getData("/root")));

			zkoperator.create("/root/child1", data);
			System.out.println(Arrays.toString(zkoperator.getData("/root/child1")));

			zkoperator.create("/root/child2", data);
			System.out.println(Arrays.toString(zkoperator.getData("/root/child2")));

			String zktest = "ZooKeeper的Java API测试";

			zkoperator.create("/root/child3", zktest.getBytes());
			log.debug("获取设置的信息：" + new String(zkoperator.getData("/root/child3")));
			// 中文处理
			// String zktest="ZooKeeper的Java API测试";
			// zkoperator.create("/root/child5", zktest.getBytes("utf-8"));
			// log.debug("获取设置的信息："+new
			// String(zkoperator.getData("/root/child5"),"utf-8"));

			System.out.println("节点孩子信息:");
			zkoperator.getChild("/root");

			zkoperator.close();

		} catch (Exception e) {
			e.printStackTrace();
		}

	}
}