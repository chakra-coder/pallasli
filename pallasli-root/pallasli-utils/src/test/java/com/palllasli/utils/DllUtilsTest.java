package com.palllasli.utils;

import org.junit.Test;


public class DllUtilsTest {

	 @Test
	 public void checkFinger() {
	// String regFingertpl =
	// "0301491f0000800e800e000e000e0006000600060006000600060006000e000e000e800e800e801e00000000000000000000000000000000328725fe1221df3e1d29607e46b55b9e1c8aa19f0f17e01f5c9e20df3e2a1e9f12b35f5f1f3d1e7f3e3fc21f3b91e4fc5936a97c2a14e2bd5732e81a2d104bfb329222db4196657b4e198b5b521bcb585c2d931947940cf61b238a364887d17750af9ab7539489d4211fc9f456a462144e2061951da0e19552a89df0000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000301441f0000c01e800e800e800600060006000600060006000600060006000600068006800e800e0000000000000000000000000000000055840016348f267e1c90e25e3c19e55e631c9ede0c29895e1cb1601e3e31dede59b7531e4d8bc09f491050ff2a9ce2df101f203f5c26e11f10ba9fbf443f1b9f57bfe99f55bba81c5418887d1a8aa3da1886a0725714dd72502c6179539245d114a8e054222ac9151a2bc9d04b31df1054b0238d462162684a1ace8800000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000";
	// String verFingertpl =
	// "030148120000f802f000f000e000c000c000c000c000c000c000c002c002c002e002e006f00efc1e00000000000000000000000000000000251421be46969fde7319259e2125df5e5b25ea3e3fabc35e2b3445fe678e223f47241d3f5b92a2f85b94e2595b8be1545c976355582169952a8dca9357189f4d65b5ab735e1cd32a00000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000301441f0000c01e800e800e800600060006000600060006000600060006000600068006800e800e0000000000000000000000000000000055840016348f267e1c90e25e3c19e55e631c9ede0c29895e1cb1601e3e31dede59b7531e4d8bc09f491050ff2a9ce2df101f203f5c26e11f10ba9fbf443f1b9f57bfe99f55bba81c5418887d1a8aa3da1886a0725714dd72502c6179539245d114a8e054222ac9151a2bc9d04b31df1054b0238d462162684a1ace8800000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000";
	//
	// int ret = 0;
	// try {
	// ret = DllUtils.checkFinger(regFingertpl, verFingertpl);
	// } catch (NativeException e) {
	// e.printStackTrace();
	// } catch (IllegalAccessException e) {
	// e.printStackTrace();
	// }
	// assertTrue(ret > 50);
	//
	// regFingertpl =
	// "301491f0008e00e800e000e000e0006000600060006000600060006000e000e000e800e800e801e00000000000000000000000000000000328725fe1221df3e1d29607e46b55b9e1c8aa19f0f17e01f5c9e20df3e2a1e9f12b35f5f1f3d1e7f3e3fc21f3b91e4fc5936a97c2a14e2bd5732e81a2d104bfb329222db4196657b4e198b5b521bcb585c2d931947940cf61b238a364887d17750af9ab7539489d4211fc9f456a462144e2061951da0e19552a89df0000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000301441f0000c01e800e800e800600060006000600060006000600060006000600068006800e800e0000000000000000000000000000000055840016348f267e1c90e25e3c19e55e631c9ede0c29895e1cb1601e3e31dede59b7531e4d8bc09f491050ff2a9ce2df101f203f5c26e11f10ba9fbf443f1b9f57bfe99f55bba81c5418887d1a8aa3da1886a0725714dd72502c6179539245d114a8e054222ac9151a2bc9d04b31df1054b0238d462162684a1ace88000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000";
	// verFingertpl =
	// "030148120000f802f000f000e000c000c000c000c000c000c000c002c002c002e002e006f00efc1e00000000000000000000000000000000251421be46969fde7319259e2125df5e5b25ea3e3fabc35e2b3445fe678e223f47241d3f5b92a2f85b94e2595b8be1545c976355582169952a8dca9357189f4d65b5ab735e1cd32a00000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000301441f0000c01e800e800e800600060006000600060006000600060006000600068006800e800e0000000000000000000000000000000055840016348f267e1c90e25e3c19e55e631c9ede0c29895e1cb1601e3e31dede59b7531e4d8bc09f491050ff2a9ce2df101f203f5c26e11f10ba9fbf443f1b9f57bfe99f55bba81c5418887d1a8aa3da1886a0725714dd72502c6179539245d114a8e054222ac9151a2bc9d04b31df1054b0238d462162684a1ace8800000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000";
	//
	// try {
	// ret = DllUtils.checkFinger(regFingertpl, verFingertpl);
	// } catch (NativeException e) {
	// e.printStackTrace();
	// } catch (IllegalAccessException e) {
	// e.printStackTrace();
	// }
	// assertTrue(ret < 50);
	 }
}
