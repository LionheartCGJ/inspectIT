package rocks.inspectit.shared.all.versioning;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.sameInstance;
import static org.mockito.Mockito.when;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.testng.annotations.Test;

import rocks.inspectit.shared.all.testbase.TestBase;
import rocks.inspectit.shared.all.version.InvalidVersionException;
import rocks.inspectit.shared.all.version.Version;
import rocks.inspectit.shared.all.version.VersionProvider;
import rocks.inspectit.shared.all.version.VersionService;

/**
 * Tests the <code>VersionService</code>
 *
 * @author Stefan Siegl
 */
public class VersionServiceTest extends TestBase {

	@InjectMocks
	VersionService versionService;

	@Mock
	VersionProvider provider;

	@Test
	public void correctVersionShouldBeCached() throws Exception {
		String correctVersion = "1.6.2.23";

		// Setup
		when(provider.readVersion()).thenReturn(correctVersion);

		Version firstCall = versionService.getVersion();
		Version secondCall = versionService.getVersion();

		assertThat(secondCall, is(sameInstance(firstCall)));
	}

	@Test(expectedExceptions = InvalidVersionException.class)
	public void incorrectVersionShouldRaiseException() throws Exception {
		// invalid
		String invalidVersion = "1.6";

		// Setup
		when(provider.readVersion()).thenReturn(invalidVersion);

		versionService.getVersion();
	}
}
