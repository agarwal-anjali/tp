package seedu.clinkedin.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.clinkedin.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import seedu.clinkedin.commons.core.index.Index;
import seedu.clinkedin.commons.util.StringUtil;
import seedu.clinkedin.logic.parser.exceptions.ParseException;
import seedu.clinkedin.model.person.Address;
import seedu.clinkedin.model.person.Email;
import seedu.clinkedin.model.person.Name;
import seedu.clinkedin.model.person.Note;
import seedu.clinkedin.model.person.Phone;
import seedu.clinkedin.model.person.Status;
import seedu.clinkedin.model.person.UniqueTagTypeMap;
import seedu.clinkedin.model.tag.Tag;
import seedu.clinkedin.model.tag.TagType;
import seedu.clinkedin.model.tag.UniqueTagList;

/**
 * Contains utility methods used for parsing strings in the various *Parser classes.
 */
public class ParserUtil {

    public static final String MESSAGE_INVALID_INDEX = "Index is not a non-zero unsigned integer.";

    /**
     * Parses {@code oneBasedIndex} into an {@code Index} and returns it. Leading and trailing whitespaces will be
     * trimmed.
     * @throws ParseException if the specified index is invalid (not non-zero unsigned integer).
     */
    public static Index parseIndex(String oneBasedIndex) throws ParseException {
        String trimmedIndex = oneBasedIndex.trim();
        if (!StringUtil.isNonZeroUnsignedInteger(trimmedIndex)) {
            throw new ParseException(MESSAGE_INVALID_INDEX);
        }
        return Index.fromOneBased(Integer.parseInt(trimmedIndex));
    }

    /**
     * Parses a {@code String name} into a {@code Name}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code name} is invalid.
     */
    public static Name parseName(String name) throws ParseException {
        requireNonNull(name);
        String trimmedName = name.trim();
        if (!Name.isValidName(trimmedName)) {
            throw new ParseException(Name.MESSAGE_CONSTRAINTS);
        }
        return new Name(trimmedName);
    }

    /**
     * Parses a {@code String phone} into a {@code Phone}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code phone} is invalid.
     */
    public static Phone parsePhone(String phone) throws ParseException {
        requireNonNull(phone);
        String trimmedPhone = phone.trim();
        if (!Phone.isValidPhone(trimmedPhone)) {
            throw new ParseException(Phone.MESSAGE_CONSTRAINTS);
        }
        return new Phone(trimmedPhone);
    }

    /**
     * Parses a {@code String clinkedin} into an {@code Address}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code clinkedin} is invalid.
     */
    public static Address parseAddress(String address) throws ParseException {
        requireNonNull(address);
        String trimmedAddress = address.trim();
        if (!Address.isValidAddress(trimmedAddress)) {
            throw new ParseException(Address.MESSAGE_CONSTRAINTS);
        }
        return new Address(trimmedAddress);
    }

    /**
     * Parses a {@code String email} into an {@code Email}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code email} is invalid.
     */
    public static Email parseEmail(String email) throws ParseException {
        requireNonNull(email);
        String trimmedEmail = email.trim();
        if (!Email.isValidEmail(trimmedEmail)) {
            throw new ParseException(Email.MESSAGE_CONSTRAINTS);
        }
        return new Email(trimmedEmail);
    }

    /**
     * Parses a {@code String tag} into a {@code Tag}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code tag} is invalid.
     */
    public static UniqueTagList parseTagList(List<String> tags) throws ParseException {
        requireNonNull(tags);
        UniqueTagList tagList = new UniqueTagList();
        for (String t : tags) {
            if (!Tag.isValidTagName(t.trim())) {
                throw new ParseException(Tag.MESSAGE_CONSTRAINTS);
            }
            tagList.add(new Tag(t.trim()));
        }
        return tagList;
    }

    /**
     * Parses a {@code String status} into a {@code Status}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code status} is invalid.
     */
    public static Status parseStatus(String status) throws ParseException {
        requireNonNull(status);
        String trimmedStatus = status.trim();
        if (!Status.isValidStatus(trimmedStatus)) {
            throw new ParseException(Status.MESSAGE_CONSTRAINTS);
        }
        return new Status(trimmedStatus);
    }

    /**
     * Parses a {@code String note} into a {@code Note}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code note} is invalid.
     */
    public static Note parseNote(String note) throws ParseException {
        requireNonNull(note);
        return new Note(note.trim());
    }

    /**
     * Parses {@code Collection<String> names} into a {@code Set<Name>}.
     */
    public static Set<Name> parseNames(Collection<String> names) throws ParseException {
        requireNonNull(names);
        final Set<Name> nameSet = new HashSet<>();
        for (String nameName : names) {
            nameSet.add(parseName(nameName));
        }
        return nameSet;
    }

    /**
     * Parses {@code Collection<String> phones} into a {@code Set<Phone>}.
     */
    public static Set<Phone> parsePhones(Collection<String> phones) throws ParseException {
        requireNonNull(phones);
        final Set<Phone> phoneSet = new HashSet<>();
        for (String phoneName : phones) {
            phoneSet.add(parsePhone(phoneName));
        }
        return phoneSet;
    }

    /**
     * Parses {@code Collection<String> emails} into a {@code Set<Email>}.
     */
    public static Set<Email> parseEmails(Collection<String> emails) throws ParseException {
        requireNonNull(emails);
        final Set<Email> emailSet = new HashSet<>();
        for (String emailName : emails) {
            emailSet.add(parseEmail(emailName));
        }
        return emailSet;
    }

    /**
     * Parses {@code Collection<String> addresss} into a {@code Set<Address>}.
     */
    public static Set<Address> parseAddresses(Collection<String> addresses) throws ParseException {
        requireNonNull(addresses);
        final Set<Address> addressSet = new HashSet<>();
        for (String addressName : addresses) {
            addressSet.add(parseAddress(addressName));
        }
        return addressSet;
    }

    /**
     * Parses {@code Collection<String> tags} into a {@code Set<Tag>}.
     */
    public static UniqueTagTypeMap parseTags(Map<Prefix, List<String>> tags) throws ParseException {
        requireNonNull(tags);
        final UniqueTagTypeMap tagMap = new UniqueTagTypeMap();
        Map<TagType, UniqueTagList> tagTypeMap = new HashMap<>();
        for (Prefix tagName : tags.keySet()) {
            if (tags.get(tagName).size() != 0) {
                tagTypeMap.put(UniqueTagTypeMap.getTagType(tagName), parseTagList(tags.get(tagName)));
            }
        }
        tagMap.setTagTypeMap(tagTypeMap);
        return tagMap;
    }

    /**
     * Parses a {@code String prefix} into a {@code Prefix}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code prefix} is invalid.
     */
    public static Prefix parsePrefix(String prefix) throws ParseException {
        requireNonNull(prefix);
        String trimmedPrefix = prefix.trim();
        if (!Prefix.isValidPrefixName(trimmedPrefix)) {
            throw new ParseException(Prefix.MESSAGE_CONSTRAINTS);
        }
        return new Prefix(trimmedPrefix + "/");
    }

    /**
     * Parses a {@code String tagType} into a {@code TagType}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code tagType} is invalid.
     */
    public static TagType parseTagType(String tagType, String prefix) throws ParseException {
        requireAllNonNull(tagType, prefix);
        String trimmedTagType = tagType.trim();
        if (!TagType.isValidTagType(trimmedTagType)) {
            throw new ParseException(TagType.MESSAGE_CONSTRAINTS);
        }
        Prefix pref = parsePrefix(prefix);
        return new TagType(trimmedTagType, pref);
    }

    /**
     * Parses a {@code String tagType} into a {@code TagType}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code tagType} is invalid.
     */
    public static TagType parseTagType(String tagType, Prefix prefix) throws ParseException {
        requireNonNull(tagType);
        String trimmedTagType = tagType.trim();
        if (!TagType.isValidTagType(trimmedTagType)) {
            throw new ParseException(TagType.MESSAGE_CONSTRAINTS);
        }
        return new TagType(trimmedTagType, prefix);
    }

    /**
     * Splits a hyphen.
     * @param oldNew Hyphenated String.
     * @return Array of Strings consisting of strings preceding and following the hyphen.
     * @throws ParseException If argument format incorrect.
     */
    public static String[] parseHyphen(String oldNew) throws ParseException {
        String[] oldNewPair = oldNew.split("-", 2);
        if (oldNewPair.length != 2) {
            throw new ParseException("Old and new tag types and tag prefixes must be separated by a hyphen!");
        }
        oldNewPair[0] = oldNewPair[0].trim();
        oldNewPair[1] = oldNewPair[1].trim();
        return oldNewPair;
    }

    /**
     * Parses {@code Collection<String> statuses} into a {@code Set<Status>}.
     */
    public static Set<Status> parseStatuses(Collection<String> statuses) throws ParseException {
        requireNonNull(statuses);
        final Set<Status> statusSet = new HashSet<>();
        for (String statusName : statuses) {
            statusSet.add(parseStatus(statusName));
        }
        return statusSet;
    }

    /**
     * Parses {@code Collection<String> notes} into a {@code Set<Note>}.
     */
    public static Set<Note> parseNotes(Collection<String> notes) throws ParseException {
        requireNonNull(notes);
        final Set<Note> noteSet = new HashSet<>();
        for (String noteName : notes) {
            noteSet.add(parseNote(noteName));
        }
        return noteSet;
    }

}