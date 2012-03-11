package clear.cdb.js.test;

import org.junit.Test;

import com.farata.cdb.annotations.helper.AnnotationsHelper;

public class HelperTests {
	@Test
	public void storeNameTest() {
		System.out.println(AnnotationsHelper.getStoreNameFull("com.farata.some.dto.SomeDTO"));
		System.out.println(AnnotationsHelper.getStoreNameShort("com.farata.some.dto.SomeDTO"));
		System.out.println(AnnotationsHelper.getStoreNameFullGen("com.farata.some.dto.SomeDTO"));
		System.out.println(AnnotationsHelper.getStorePackage("com.farata.some.dto.SomeDTO"));
		System.out.println(AnnotationsHelper.getStorePath("com.farata.some.dto.SomeDTO"));
		
		System.out.println("======");
		System.out.println(AnnotationsHelper.getModelNameFull("com.farata.some.dto.SomeDTO"));
		System.out.println(AnnotationsHelper.getModelNameShort("com.farata.some.dto.SomeDTO"));
		System.out.println(AnnotationsHelper.getModelNameFullGen("com.farata.some.dto.SomeDTO"));
		System.out.println(AnnotationsHelper.getModelPackage("com.farata.some.dto.SomeDTO"));
		System.out.println(AnnotationsHelper.getModelPath("com.farata.some.dto.SomeDTO"));

	}
}
